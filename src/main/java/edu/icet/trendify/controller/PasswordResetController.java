package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.PasswordResetDto;
import edu.icet.trendify.service.PasswordResetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password-reset")
@RequiredArgsConstructor
@CrossOrigin
public class PasswordResetController {
    private final PasswordResetService passwordResetService;

    @PostMapping("/request")
    public ResponseEntity<ResponseDto<String>> sendPasswordResetEmail(@RequestParam(value = "email",required = true) String email) {
        return passwordResetService.sendPasswordResetEmail(email);
    }
    @PostMapping("/reset")
    public ResponseEntity<ResponseDto<String>> resetPassword(@RequestBody @Valid PasswordResetDto resetDto) {
        return passwordResetService.resetPassword(resetDto);
    }
}
