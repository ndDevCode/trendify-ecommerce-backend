package edu.icet.trendify.util.converters;

import edu.icet.trendify.util.enums.PaymentType;
import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;

@Converter(autoApply = true)
public class PaymentTypeConverter implements AttributeConverter<PaymentType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentType paymentType) {

        return switch (paymentType){
            case ONLINE -> 1;
            case CASH_ON_DELIVERY -> 2;
        };
    }

    @Override
    public PaymentType convertToEntityAttribute(Integer integer) {

        return switch (integer){
            case 1 -> PaymentType.ONLINE;
            case 2 -> PaymentType.CASH_ON_DELIVERY;
            default -> null;
        };
    }
}
