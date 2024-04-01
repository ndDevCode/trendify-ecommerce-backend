package edu.icet.trendify.dto.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.inventory.SubInventoryEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SubInventoryDto(
        @NotNull(message = "Inventory Id should not be empty")
        Long inventoryId,
        @NotNull(message = "Color Id should not be empty")
        Integer colorId,
        @NotNull(message = "Size Id should not be empty")
        Integer sizeId,
        @NotNull(message = "Quantity should not be empty")
        Integer quantity,
        @NotNull(message = "Is Sold Out Value should not be empty")
        Boolean isSoldOut

) implements Serializable {
}