package edu.icet.trendify.service;


import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.PasswordResetDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PasswordResetService {
    ResponseEntity<ResponseDto<String>> sendPasswordResetEmail(String email);
    ResponseEntity<ResponseDto<String>> resetPassword(PasswordResetDto resetDto);
}
