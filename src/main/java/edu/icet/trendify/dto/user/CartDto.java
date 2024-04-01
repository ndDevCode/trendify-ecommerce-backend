package edu.icet.trendify.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.entity.user.CartEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link CartEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record CartDto(
        Long id,
        @NotNull(message = "Customer Id should not be empty")
        Long customerId,
        @NotNull(message = "Cart products should not be empty") @Size(min = 1)
        List<CartProductDto> cartProductList
) implements Serializable {
}