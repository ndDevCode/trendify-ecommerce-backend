package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.inventory.SubInventoryDto;
import edu.icet.trendify.entity.inventory.SubInventoryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubInventoryMapper {
    SubInventoryEntity toEntity(SubInventoryDto subInventoryDto);

    SubInventoryDto toDto(SubInventoryEntity subInventoryEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubInventoryEntity partialUpdate(SubInventoryDto subInventoryDto, @MappingTarget SubInventoryEntity subInventoryEntity);
}