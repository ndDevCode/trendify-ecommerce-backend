package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AdminDto;
import edu.icet.trendify.entity.user.UserEntity;
import edu.icet.trendify.repository.user.UserRepository;
import edu.icet.trendify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<UserEntity> getAdminById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public ResponseEntity<ResponseDto<AdminDto>> updatePassword(String email, String password){
        try {
            userRepository.updatePassword(password, email);
            return new ResponseEntity<>(
                    ResponseDto.success(null,"Password updated successfully!"), HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseDto.error("Error while updating password!"), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
