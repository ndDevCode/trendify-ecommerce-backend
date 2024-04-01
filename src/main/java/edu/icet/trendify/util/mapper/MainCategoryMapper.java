package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.product.MainCategoryDto;
import edu.icet.trendify.entity.product.MainCategoryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MainCategoryMapper {
    MainCategoryEntity toEntity(MainCategoryDto mainCategoryDto);

    MainCategoryDto toDto(MainCategoryEntity mainCategoryEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MainCategoryEntity partialUpdate(MainCategoryDto mainCategoryDto, @MappingTarget MainCategoryEntity mainCategoryEntity);
}