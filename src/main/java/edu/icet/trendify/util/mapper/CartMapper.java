package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.user.CartDto;
import edu.icet.trendify.entity.user.CartEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartMapper {
    CartEntity toEntity(CartDto cartDto);

    @AfterMapping
    default void linkCartProductList(@MappingTarget CartEntity cartEntity) {
        cartEntity.getCartProductList().forEach(cartProductList -> cartProductList.setCart(cartEntity));
    }

    CartDto toDto(CartEntity cartEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CartEntity partialUpdate(CartDto cartDto, @MappingTarget CartEntity cartEntity);
}