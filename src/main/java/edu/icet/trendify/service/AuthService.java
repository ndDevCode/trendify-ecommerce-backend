package edu.icet.trendify.service;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AuthResponseDto;
import edu.icet.trendify.dto.user.LoginDto;
import edu.icet.trendify.dto.user.RefreshTokenDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseDto<AuthResponseDto<?>>> refreshUserToken(RefreshTokenDto refreshTokenDto);
    ResponseEntity<ResponseDto<AuthResponseDto<?>>> authenticateUserLogin(LoginDto loginDto);
}
