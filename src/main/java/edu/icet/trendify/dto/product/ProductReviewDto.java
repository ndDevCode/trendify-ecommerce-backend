package edu.icet.trendify.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.product.ProductReviewEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductReviewDto(
        @NotNull(message = "Customer Id should not be empty")
        Long cusId,
        @NotNull(message = "Product Id should not be empty")
        Long productId,
        @NotNull(message = "Review should not be empty")
        String review,
        @NotNull(message = "Rating Id should not be empty")
        @Digits(message = "Invalid Format", integer = 1,fraction = 0)
        @DecimalMin(value = "1")
        @DecimalMax(value = "5")
        Short rating
) implements Serializable {
}