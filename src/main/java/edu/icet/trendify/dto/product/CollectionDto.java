package edu.icet.trendify.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.entity.product.CollectionEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link CollectionEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CollectionDto(
        Integer id,
        @NotEmpty(message = "Name should not be empty")
        String name,
        @NotEmpty(message = "Discount rate should not be empty")
        Double discountRate,
        @NotEmpty(message = "Created time should not be empty")
        LocalDateTime createdAt,
        @NotEmpty(message = "Active status should not be empty")
        Boolean isActive,
        @NotEmpty(message = "Product list should not be empty")
        @Size(min=2, message = "At least 2 products should be added to a collection")
        List<ProductDto> productList
) implements Serializable {
}