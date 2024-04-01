package edu.icet.trendify.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.dto.billing.BillingInfoDto;
import edu.icet.trendify.entity.order.OrderEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link OrderEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderDto(
        Long id,
        @NotNull(message = "Contact should not be empty")
        String contact,
        @NotNull(message = "Address Id should not be empty")
        Long addressId,
        @NotNull(message = "Customer Id should not be empty")
        Long customerId,
        @NotNull(message = "BillingInfo should not be empty")
        BillingInfoDto billingInfo,
        @NotNull(message = "Order Products should not be empty")
        @Size(min = 1,message = "Minimum One product should be in the order")
        List<OrderProductDto> orderProducts
) implements Serializable {
}