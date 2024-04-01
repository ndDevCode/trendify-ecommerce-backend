package edu.icet.trendify.dto.user;

import edu.icet.trendify.util.constant.RegexPattern;
import edu.icet.trendify.util.constant.ValidationInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDto {
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid Email")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @Pattern(regexp = RegexPattern.PASSWORD, message = ValidationInfo.PASSWORD_PATTERN)
    private String password;
}