package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.product.ProductDto;
import edu.icet.trendify.entity.product.ProductEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductEntity toEntity(ProductDto productDto);

    ProductDto toDto(ProductEntity productEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductEntity partialUpdate(ProductDto productDto, @MappingTarget ProductEntity productEntity);
}