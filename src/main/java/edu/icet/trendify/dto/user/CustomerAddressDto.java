package edu.icet.trendify.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.user.CustomerAddressEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerAddressDto(
        @NotNull(message = "CustomerId should not be empty")
        Long cusId,
        Long addressId,
        @NotNull(message = "House No should not be empty")
        String houseNo,
        @NotNull(message = "Street should not be empty")
        String street,
        @NotNull(message = "City should not be empty")
        String city,
        @NotNull(message = "Province should not be empty")
        String province,
        @NotNull(message = "Postal Code should not be empty")
        @Digits(integer = 5,fraction = 0, message = "Invalid Postal Code format")
        String postalCode,
        @NotNull(message = "IsDefault value should not be empty")
        Boolean isDefault
) implements Serializable {
}