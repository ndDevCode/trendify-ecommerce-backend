package edu.icet.trendify.util.converters;

import edu.icet.trendify.util.enums.PaymentMethod;
import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;

@Converter(autoApply = true)
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentMethod paymentMethod) {

        return switch (paymentMethod){
            case CASH -> 1;
            case DEBIT_CARD -> 2;
            case CREDIT_CARD -> 3;
            case BANK_TRANSFER -> 4;
        };
    }

    @Override
    public PaymentMethod convertToEntityAttribute(Integer integer) {

        return switch (integer){
            case 1 -> PaymentMethod.CASH;
            case 2 -> PaymentMethod.DEBIT_CARD;
            case 3 -> PaymentMethod.CREDIT_CARD;
            case 4 -> PaymentMethod.BANK_TRANSFER;
            default -> null;
        };
    }
}
