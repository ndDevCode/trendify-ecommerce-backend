package edu.icet.trendify.dto.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.entity.inventory.InventoryEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link InventoryEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record InventoryDto(
        Long id,
        @NotEmpty(message = "Total Quantity should not be empty")
        Long totalQuantity,
        @NotEmpty(message = "Total price should not be empty")
        Double totalPrice,
        @NotEmpty(message = "Unit price should not be empty")
        Double unitPrice,
        @NotEmpty(message = "Created time should not be empty")
        LocalDateTime createdAt,
        @NotEmpty(message = "Reorder level should not be empty")
        Integer reorderLevel,
        @NotEmpty(message = "Is released value should not be empty")
        Boolean isReleased,
        String remarks,
        @NotEmpty(message = "Sub Inventories should not be empty")
        @Size(min = 1)
        List<SubInventoryDto> subInventoryList
) implements Serializable {
}