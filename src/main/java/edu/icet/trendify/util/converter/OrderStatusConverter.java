package edu.icet.trendify.util.converter;

import edu.icet.trendify.util.enums.OrderStatus;
import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {

        return switch (orderStatus){
            case PENDING -> 1;
            case PROCESSING -> 2;
            case DISPATCHED -> 3;
            case DELIVERED -> 4;
            case CANCELED -> 5;
            case RETURNED -> 6;
        };
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer integer) {

        return switch (integer){
            case 1 -> OrderStatus.PENDING;
            case 2 -> OrderStatus.PROCESSING;
            case 3 -> OrderStatus.DISPATCHED;
            case 4 -> OrderStatus.DELIVERED;
            case 5 -> OrderStatus.CANCELED;
            case 6 -> OrderStatus.RETURNED;
            default -> null;
        };
    }
}
