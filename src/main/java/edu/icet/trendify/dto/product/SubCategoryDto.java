package edu.icet.trendify.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.product.SubCategoryEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SubCategoryDto(
        Integer id,
        @NotNull(message = "Name should not be empty")
        String name
) implements Serializable {
}