package edu.icet.trendify.service;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.inventory.InventoryDto;
import edu.icet.trendify.dto.inventory.SubInventoryDto;
import edu.icet.trendify.entity.id.SubInventoryId;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    ResponseDto<InventoryDto> createInventory(InventoryDto inventoryDto);
    ResponseDto<InventoryDto> updateInventory(InventoryDto inventoryDto);
    Boolean updateInventoryRemark(Long inventoryId,String remark);
    Boolean updateReleaseStatus(Long inventoryId, Boolean isReleased);
    Boolean updateReOrderLevel(Long inventoryId, Integer reorderLevel);
    List<InventoryDto> getAllInventory();
    Optional<InventoryDto> getInventoryById(Long id);
    List<SubInventoryDto> getSubInventoryById(Long inventoryId);
    ResponseDto<SubInventoryDto> updateSubInventoryById(SubInventoryDto subInventoryDto);
}
