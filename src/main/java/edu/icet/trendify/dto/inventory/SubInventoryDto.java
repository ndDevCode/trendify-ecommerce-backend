package edu.icet.trendify.dto.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.inventory.SubInventoryEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SubInventoryDto(
        @NotEmpty(message = "Inventory Id should not be empty")
        Long inventoryId,
        @NotEmpty(message = "Color Id should not be empty")
        Integer colorId,
        @NotEmpty(message = "Size Id should not be empty")
        Integer sizeId,
        @NotEmpty(message = "Quantity should not be empty")
        Integer quantity,
        @NotEmpty(message = "Is Sold Out Value should not be empty")
        Boolean isSoldOut

) implements Serializable {
}