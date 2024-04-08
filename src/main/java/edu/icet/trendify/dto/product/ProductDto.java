package edu.icet.trendify.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.product.ProductEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductDto(
        Integer id,
        @NotEmpty(message = "Name should not be empty")
        String name,
        @NotEmpty(message = "Description should not be empty")
        String description,
        Double discount,
        @NotEmpty(message = "Image should not be empty")
        String image
) implements Serializable {
}