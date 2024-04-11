package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.user.CartProductDto;
import edu.icet.trendify.entity.user.CartProductEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartProductMapper {
    CartProductEntity toEntity(CartProductDto cartProductDto);

    CartProductDto toDto(CartProductEntity cartProductEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CartProductEntity partialUpdate(CartProductDto cartProductDto, @MappingTarget CartProductEntity cartProductEntity);
}