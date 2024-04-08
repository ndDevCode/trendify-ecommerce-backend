package edu.icet.trendify.dto.user;

import edu.icet.trendify.util.constant.RegexPattern;
import edu.icet.trendify.util.constant.ValidationInfo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record PasswordResetDto(
        @NotEmpty(message = "Token should not be empty")
        String token,
        @NotEmpty(message = "Password should not be empty")
        @Pattern(regexp = RegexPattern.PASSWORD, message = ValidationInfo.PASSWORD_PATTERN)
        String password
) implements Serializable {
}
