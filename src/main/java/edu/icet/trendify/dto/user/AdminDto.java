package edu.icet.trendify.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.util.constant.RegexPattern;
import edu.icet.trendify.util.constant.ValidationInfo;
import edu.icet.trendify.util.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.user.AdminEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record AdminDto(
        Long id,
        @NotNull(message = "Firstname should not be empty")
        String firstName,
        @NotNull(message = "Lastname should not be empty")
        String lastName,
        @NotNull(message = "Email Should not be empty")
        @Email(message = "Not a valid email")
        String email,
        @NotNull(message = "Password should not be empty")
        @Pattern(regexp = RegexPattern.PASSWORD, message = ValidationInfo.PASSWORD_PATTERN)
        String password,
        @NotNull(message = "Role should not be empty")
        Role role,
        @NotNull(message = "Contact should not be empty")
        String contact
) implements Serializable {
}