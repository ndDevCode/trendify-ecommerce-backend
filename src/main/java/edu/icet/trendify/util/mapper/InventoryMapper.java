package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.inventory.InventoryDto;
import edu.icet.trendify.entity.inventory.InventoryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryMapper {
    @Mapping(source = "productId", target = "product.id")
    InventoryEntity toEntity(InventoryDto inventoryDto);

    @Mapping(source = "product.id", target = "productId")
    InventoryDto toDto(InventoryEntity inventoryEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InventoryEntity partialUpdate(InventoryDto inventoryDto, @MappingTarget InventoryEntity inventoryEntity);
}