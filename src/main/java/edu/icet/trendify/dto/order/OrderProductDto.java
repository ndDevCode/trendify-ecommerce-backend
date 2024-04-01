package edu.icet.trendify.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.order.OrderProductEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderProductDto(
        Long orderId,
        @NotNull(message = "Product Id should not be empty")
        Long productId,
        @NotNull(message = "Quantity should not be empty")
        @DecimalMin(value = "1")
        Integer quantity,
        @NotNull(message = "Color Id should not be empty")
        String colorId
) implements Serializable {
}