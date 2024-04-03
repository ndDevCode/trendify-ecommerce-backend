package edu.icet.trendify.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.util.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link edu.icet.trendify.entity.user.RoleEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RoleDto(
        @NotNull(message = "Role should not be empty")
        @Size(min = 1, message = "Role should have at least one role")
        List<Role> role,
        @NotNull(message = "User Id should not be empty")
        Long userId
) implements Serializable {
}