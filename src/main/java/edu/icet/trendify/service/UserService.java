package edu.icet.trendify.service;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AdminDto;
import edu.icet.trendify.entity.user.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> getUserByEmail(String email);
    Optional<UserEntity> getAdminById(Long id);
    ResponseEntity<ResponseDto<AdminDto>> updatePassword(String email, String password);
}
