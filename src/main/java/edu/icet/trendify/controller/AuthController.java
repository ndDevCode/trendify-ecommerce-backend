package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AuthResponseDto;
import edu.icet.trendify.dto.user.LoginDto;
import edu.icet.trendify.dto.user.RefreshTokenDto;
import edu.icet.trendify.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<ResponseDto<AuthResponseDto<?>>> loginUser(@RequestBody @Valid LoginDto loginDto) {
        return authService.authenticateUserLogin(loginDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseDto<AuthResponseDto<?>>> refreshUserToken(@RequestBody @Valid RefreshTokenDto refreshTokenDto) {
        return authService.refreshUserToken(refreshTokenDto);
    }
}
