package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.inventory.InventoryDto;
import edu.icet.trendify.entity.inventory.InventoryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryMapper {
    InventoryEntity toEntity(InventoryDto inventoryDto);

    InventoryDto toDto(InventoryEntity inventoryEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InventoryEntity partialUpdate(InventoryDto inventoryDto, @MappingTarget InventoryEntity inventoryEntity);
}