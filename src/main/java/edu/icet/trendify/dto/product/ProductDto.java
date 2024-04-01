package edu.icet.trendify.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.product.ProductEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductDto(
        Integer id,
        @NotNull(message = "Name should not be empty")
        String name,
        @NotNull(message = "Description should not be empty")
        String description,
        Double discount,
        @NotNull(message = "Image should not be empty")
        String image
) implements Serializable {
}