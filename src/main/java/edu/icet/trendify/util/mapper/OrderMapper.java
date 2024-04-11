package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.order.OrderDto;
import edu.icet.trendify.entity.order.OrderEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    OrderEntity toEntity(OrderDto orderDto);

    @Mapping(source = "customer.id", target = "customerId")
    OrderDto toDto(OrderEntity orderEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderEntity partialUpdate(OrderDto orderDto, @MappingTarget OrderEntity orderEntity);
}