package edu.icet.trendify.dto.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.entity.inventory.ColorEntity;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link ColorEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ColorDto(
        Integer id,
        @NotEmpty(message = "Name should not be empty")
        String name,
        @NotEmpty(message = "Color Code should not be empty")
        String code
) implements Serializable {
}