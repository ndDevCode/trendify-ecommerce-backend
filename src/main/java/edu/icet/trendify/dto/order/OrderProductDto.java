package edu.icet.trendify.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.order.OrderProductEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderProductDto(
        Long orderId,
        @NotEmpty(message = "Product Id should not be empty")
        Integer productId,
        @NotEmpty(message = "Quantity should not be empty")
        @DecimalMin(value = "1")
        Integer quantity,
        @NotEmpty(message = "Color Id should not be empty")
        String colorId,
        @NotNull(message = "Price should not be null")
        @Positive(message = "Value should be positive")
        Double price
) implements Serializable {
}