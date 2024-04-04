package edu.icet.trendify.service;


import edu.icet.trendify.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PasswordResetService {
    ResponseEntity<ResponseDto<String>> sendPasswordResetEmail(String email);
    ResponseEntity<ResponseDto<String>> resetPassword(Map<String,String> resetData);
}
