package edu.icet.trendify.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.user.ContactEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ContactDto(
        @NotEmpty(message = "Contact should not be empty")
        String contact,
        @NotNull(message = "Customer Id should not be null")
        Long cusId
) implements Serializable {
}