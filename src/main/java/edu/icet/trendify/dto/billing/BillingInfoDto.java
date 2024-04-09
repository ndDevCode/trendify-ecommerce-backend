package edu.icet.trendify.dto.billing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.trendify.util.enums.PaymentMethod;
import edu.icet.trendify.util.enums.PaymentType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.billing.BillingInfoEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BillingInfoDto(
        Long id,
        @NotEmpty(message = "Total Price should not be empty")
        @Positive(message = "Value should be positive")
        Double totalPrice,
        Double discount,
        Double tax,
        @NotEmpty(message = "Payment Type should not be empty")
        PaymentType paymentType,
        @NotEmpty(message = "Payment Method should not be empty")
        PaymentMethod paymentMethod,
        @NotEmpty(message = "Order Id should not be empty")
        Long orderId
) implements Serializable {
}