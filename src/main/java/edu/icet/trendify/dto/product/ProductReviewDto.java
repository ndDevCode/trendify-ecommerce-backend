package edu.icet.trendify.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.product.ProductReviewEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductReviewDto(
        @NotEmpty(message = "Customer Id should not be empty")
        Long cusId,
        @NotEmpty(message = "Product Id should not be empty")
        Long productId,
        @NotEmpty(message = "Review should not be empty")
        String review,
        @NotEmpty(message = "Rating Id should not be empty")
        @Digits(message = "Invalid Format", integer = 1,fraction = 0)
        @DecimalMin(value = "1", message = "Rating should be between 1 and 5")
        @DecimalMax(value = "5", message = "Rating should be between 1 and 5")
        Short rating
) implements Serializable {
}