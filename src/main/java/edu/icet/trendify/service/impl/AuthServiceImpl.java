package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AuthResponseDto;
import edu.icet.trendify.dto.user.LoginDto;
import edu.icet.trendify.dto.user.RefreshTokenDto;
import edu.icet.trendify.entity.user.JwtTokenEntity;
import edu.icet.trendify.entity.user.RefreshTokenEntity;
import edu.icet.trendify.entity.user.UserEntity;
import edu.icet.trendify.repository.user.*;
import edu.icet.trendify.security.JWTGenerator;
import edu.icet.trendify.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenRepository jwtTokenRepository;

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    @Transactional
    @Override
    public ResponseEntity<ResponseDto<AuthResponseDto>> refreshUserToken(RefreshTokenDto refreshTokenDto) {

        if (jwtGenerator.validateRefreshToken(refreshTokenDto.getRefreshToken())
                && refreshTokenRepository.existsById(refreshTokenDto.getRefreshToken())) {
            String usernameFromJWT = jwtGenerator.getUsernameFromJWT(refreshTokenDto.getRefreshToken());
            UserEntity userEntity = userRepository.findByEmail(usernameFromJWT).orElse(null);

            assert userEntity != null;
            String accessToken = jwtGenerator.generateRefreshedAccessToken(
                            userEntity.getEmail(),
                            userEntity.getUserRole()
                            .stream()
                            .map(role -> role.getRole().getRole())
                            .toList()
            );

            jwtTokenRepository.deleteByUser(userEntity.getId());
            jwtTokenRepository.save(new JwtTokenEntity(accessToken,false,userEntity));

            return new ResponseEntity<>(
                    ResponseDto.success(AuthResponseDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenDto.getRefreshToken())
                            .tokenType("Bearer ")
                            .build(), "Token refreshed successfully!"),
                    HttpStatus.OK
            );
        }

        refreshTokenRepository.deleteById(refreshTokenDto.getRefreshToken());
        return new ResponseEntity<>(ResponseDto.error("Refresh token invalid or expired! Please Login."), HttpStatus.FORBIDDEN);

    }

    @Transactional
    @Override
    public ResponseEntity<ResponseDto<AuthResponseDto>> authenticateUserLogin(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));

        Optional<UserEntity> userOptional = userRepository.findByEmail(loginDto.getEmail());

        if (authentication.isAuthenticated() && userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            String refreshToken = jwtGenerator.generateRefreshToken(authentication.getName());
            JwtTokenEntity jwtTokenEntity = new JwtTokenEntity(token, false, userEntity);
            RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
                    .refreshToken(refreshToken)
                    .user(userEntity)
                    .build();

            refreshTokenRepository.deleteByUser(userEntity.getId());
            jwtTokenRepository.deleteByUser(userEntity.getId());

            jwtTokenRepository.save(jwtTokenEntity);
            refreshTokenRepository.save(refreshTokenEntity);

            return new ResponseEntity<>(ResponseDto.success(
                    AuthResponseDto.builder()
                            .accessToken(token)
                            .refreshToken(refreshToken)
                            .tokenType("Bearer ")
                            .build(),
                    "User logged in successfully!"
            ),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(ResponseDto.error("Invalid credentials"), HttpStatus.UNAUTHORIZED);
    }
}
