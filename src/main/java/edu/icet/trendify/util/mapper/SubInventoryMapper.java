package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.inventory.SubInventoryDto;
import edu.icet.trendify.entity.inventory.SubInventoryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubInventoryMapper {
    SubInventoryEntity toEntity(SubInventoryDto subInventoryDto);

    @Mapping(source = "inventory.id", target = "inventoryId")
    @Mapping(source = "color.id", target = "colorId")
    @Mapping(source = "size.id", target = "sizeId")
    SubInventoryDto toDto(SubInventoryEntity subInventoryEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubInventoryEntity partialUpdate(SubInventoryDto subInventoryDto, @MappingTarget SubInventoryEntity subInventoryEntity);
}