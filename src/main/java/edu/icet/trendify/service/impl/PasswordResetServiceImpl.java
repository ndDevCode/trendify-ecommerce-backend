package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.entity.user.PasswordResetTokenEntity;
import edu.icet.trendify.entity.user.UserEntity;
import edu.icet.trendify.repository.user.PasswordResetTokenRepository;
import edu.icet.trendify.service.EmailService;
import edu.icet.trendify.service.PasswordResetService;
import edu.icet.trendify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final UserService userService;
    private final EmailService emailService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ResponseDto<String>> sendPasswordResetEmail(String email) {

        Optional<UserEntity> user = userService.getUserByEmail(email);

        if (user.isEmpty()) {
            return new ResponseEntity<>(
                    ResponseDto.error("User not found!"), HttpStatus.NOT_FOUND
            );
        }

        PasswordResetTokenEntity token = createToken(user.get());

        String resetLink = "http://localhost:3000/reset-password?token=" + token.getToken();
        String emailSubject = "Password Reset Request";

        String emailBody = "Dear Madam/Sir,\n\n" +
                "You have requested to reset your password." +
                "Click the following link to reset your password:\n" + resetLink +
                "\n\nIf you haven't requested this. Please ignore this email. Thank You!" +
                "\n\nRegards\nTrendify Team";

        emailService.sendEmail(email,emailSubject,emailBody);

        return new ResponseEntity<>(
                ResponseDto.success(null,"Password reset email sent successfully!"), HttpStatus.OK
        );
    }



    @Override
    public ResponseEntity<ResponseDto<String>> resetPassword(Map<String, String> resetData) {
        String token = resetData.get("token");
        String newPassword = resetData.get("password");

        PasswordResetTokenEntity resetToken = findByToken(token);

        if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            deleteToken(resetToken);
            return new ResponseEntity<>(
                    ResponseDto.error("Invalid or expired token!"), HttpStatus.BAD_REQUEST
            );
        }

        UserEntity user = resetToken.getUser();
        userService.updatePassword(user.getEmail(), passwordEncoder.encode(newPassword));
        deleteToken(resetToken);

        return new ResponseEntity<>(
                ResponseDto.success(null,"Password reset successfully!"), HttpStatus.OK
        );
    }

    private PasswordResetTokenEntity createToken(UserEntity user) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);

        PasswordResetTokenEntity tokenEntity = findByUserId(user.getId());
        if (tokenEntity != null && tokenEntity.getExpiryDate().isAfter(LocalDateTime.now())) {
            return tokenEntity;
        } else {
            deleteToken(tokenEntity);
        }

        PasswordResetTokenEntity passwordResetToken = new PasswordResetTokenEntity();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(expiryDate);

        return passwordResetTokenRepository.save(passwordResetToken);
    }


    public PasswordResetTokenEntity findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }


    public void deleteToken(PasswordResetTokenEntity token) {
        passwordResetTokenRepository.delete(token);
    }


    public PasswordResetTokenEntity findByUserId(Long userId) {
        return passwordResetTokenRepository.findByUserId(userId);
    }
}
