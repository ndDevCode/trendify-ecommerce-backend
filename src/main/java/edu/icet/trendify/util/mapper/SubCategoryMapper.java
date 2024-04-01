package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.product.SubCategoryDto;
import edu.icet.trendify.entity.product.SubCategoryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubCategoryMapper {
    SubCategoryEntity toEntity(SubCategoryDto subCategoryDto);

    SubCategoryDto toDto(SubCategoryEntity subCategoryEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubCategoryEntity partialUpdate(SubCategoryDto subCategoryDto, @MappingTarget SubCategoryEntity subCategoryEntity);
}