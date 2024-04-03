package edu.icet.trendify.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto {
    @NotEmpty(message = "RefreshToken should not be empty")
    private String refreshToken;
}
