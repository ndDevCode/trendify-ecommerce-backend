package edu.icet.trendify.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.util.constant.RegexPattern;
import edu.icet.trendify.util.constant.ValidationInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.user.CustomerEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerEntityDto(
        Long id,
        @NotNull(message = "Firstname should not be empty")
        @Pattern(message = "Name should contain only letters", regexp = RegexPattern.TEXT)
        String firstName,
        @NotNull(message = "Lastname should not be empty")
        @Pattern(message = "Name should contain only letters", regexp = RegexPattern.TEXT)
        String lastName,
        @NotNull(message = "Email should not be empty")
        @Email(message = "Invalid Email")
        String email,
        @NotNull(message = "Password should not be empty")
        @Pattern(regexp = RegexPattern.PASSWORD, message = ValidationInfo.PASSWORD_PATTERN)
        String password
) implements Serializable {
}