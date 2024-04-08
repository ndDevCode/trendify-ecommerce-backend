package edu.icet.trendify.dto.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.inventory.SizeEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SizeDto(
        Integer id,
        @NotEmpty(message = "Size should not be empty")
        String size
) implements Serializable {
}