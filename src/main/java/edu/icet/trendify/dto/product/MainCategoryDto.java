package edu.icet.trendify.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.product.MainCategoryEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MainCategoryDto(
        Integer id,
        @NotEmpty(message = "Name should not be empty")
        String name
) implements Serializable {
}