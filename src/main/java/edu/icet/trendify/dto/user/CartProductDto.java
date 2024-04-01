package edu.icet.trendify.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.user.CartProductEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record CartProductDto(
        @NotNull(message = "Product Id should not be empty")
        Long productId,
        @NotNull(message = "Quantity should not be empty")
        Integer quantity,
        @NotNull(message = "Color Id should not be empty")
        Integer colorId
) implements Serializable {
}