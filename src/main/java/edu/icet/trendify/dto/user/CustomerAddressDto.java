package edu.icet.trendify.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link edu.icet.trendify.entity.user.CustomerAddressEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerAddressDto(
        @NotNull(message = "CustomerId should not be null")
        Long cusId,
        Long addressId,
        @NotEmpty(message = "House No should not be empty")
        String houseNo,
        @NotEmpty(message = "Street should not be empty")
        String street,
        @NotEmpty(message = "City should not be empty")
        String city,
        @NotEmpty(message = "Province should not be empty")
        String province,
        @NotEmpty(message = "Postal Code should not be empty")
        @Digits(integer = 5,fraction = 0, message = "Invalid Postal Code format")
        String postalCode,
        @NotNull(message = "IsDefault value should not be empty")
        Boolean isDefault
) implements Serializable {
}