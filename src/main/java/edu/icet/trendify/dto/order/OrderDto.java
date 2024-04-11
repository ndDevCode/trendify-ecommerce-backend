package edu.icet.trendify.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.dto.billing.BillingInfoDto;
import edu.icet.trendify.entity.order.OrderEntity;
import edu.icet.trendify.util.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link OrderEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderDto(
        Long id,
        @NotEmpty(message = "Contact should not be empty")
        String contact,
        @NotNull(message = "Address Id should not be empty")
        Long addressId,
        @NotNull(message = "OrderStatus should not be null")
        OrderStatus orderStatus,
        @NotNull(message = "Customer Id should not be empty")
        Long customerId,
        @NotNull(message = "BillingInfo should not be empty")
        BillingInfoDto billingInfo,
        @NotEmpty(message = "Order Products should not be empty")
        @Size(min = 1,message = "Minimum One product should be in the order")
        List<OrderProductDto> orderProducts,
        LocalDateTime orderPlacedAt
) implements Serializable {
}