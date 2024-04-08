package edu.icet.trendify.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.util.constant.RegexPattern;
import edu.icet.trendify.util.constant.ValidationInfo;
import edu.icet.trendify.util.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link edu.icet.trendify.entity.user.CustomerEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record CustomerDto(
        Long id,
        @NotEmpty(message = "Firstname should not be empty")
        @Pattern(message = "Name should contain only letters", regexp = RegexPattern.TEXT)
        String firstName,
        @NotEmpty(message = "Lastname should not be empty")
        @Pattern(message = "Name should contain only letters", regexp = RegexPattern.TEXT)
        String lastName,
        @NotEmpty(message = "Email should not be empty")
        @Email(message = "Invalid Email")
        String email,
        @NotEmpty(message = "Password should not be empty")
        @Pattern(regexp = RegexPattern.PASSWORD, message = ValidationInfo.PASSWORD_PATTERN)
        String password,

        @NotEmpty(message = "Role should not be empty")
        @Size(min = 1, message = "Role should have at least one role")
        List<Role> role,
        Boolean isActive) implements Serializable {
}