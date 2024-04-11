package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.inventory.InventoryDto;
import edu.icet.trendify.dto.inventory.SubInventoryDto;
import edu.icet.trendify.entity.id.SubInventoryId;
import edu.icet.trendify.entity.inventory.InventoryEntity;
import edu.icet.trendify.entity.inventory.SubInventoryEntity;
import edu.icet.trendify.repository.inventory.ColorRepository;
import edu.icet.trendify.repository.inventory.InventoryRepository;
import edu.icet.trendify.repository.inventory.SizeRepository;
import edu.icet.trendify.repository.inventory.SubInventoryRepository;
import edu.icet.trendify.service.InventoryService;
import edu.icet.trendify.util.mapper.InventoryMapper;
import edu.icet.trendify.util.mapper.SubInventoryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final SubInventoryRepository subInventoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    private final InventoryMapper inventoryMapper;
    private final SubInventoryMapper subInventoryMapper;

    @Override
    @Transactional
    public ResponseDto<InventoryDto> createInventory(InventoryDto inventoryDto) {
        try {
            InventoryEntity inventoryEntity = inventoryMapper.toEntity(inventoryDto);
            inventoryEntity.setCreatedAt(LocalDateTime.now());
            InventoryEntity savedInventoryEntity = inventoryRepository.save(inventoryEntity);

            inventoryDto.subInventoryList().forEach(
                    subInventoryDto -> {
                        SubInventoryEntity subInventoryEntity = subInventoryMapper.toEntity(subInventoryDto);
                        subInventoryEntity.setInventoryId(savedInventoryEntity.getId());
                        subInventoryEntity.setColor(colorRepository.findById(subInventoryDto.colorId()).orElse(null));
                        subInventoryEntity.setSize(sizeRepository.findById(subInventoryDto.sizeId()).orElse(null));
                        subInventoryEntity.setInventory(savedInventoryEntity);
                        subInventoryRepository.save(subInventoryEntity);
                    }
            );

            return ResponseDto.success(inventoryDto, "Inventory created successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.error(e.getMessage());
        }
    }

    @Override
    public ResponseDto<InventoryDto> updateInventory(InventoryDto inventoryDto) {
        try{
            if(inventoryRepository.findById(inventoryDto.id()).isPresent()){
                InventoryEntity inventoryEntity = inventoryMapper.toEntity(inventoryDto);
                inventoryRepository.save(inventoryEntity);
                return ResponseDto.success(inventoryDto, "Inventory updated successfully");
            }
            return ResponseDto.error("Inventory not found");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.error(e.getMessage());
        }
    }

    @Override
    public Boolean updateInventoryRemark(Long inventoryId, String remark) {
        Optional<InventoryEntity> inventoryEntity = inventoryRepository.findById(inventoryId);
        if(inventoryEntity.isPresent()){
            inventoryEntity.get().setRemarks(remark);
            inventoryRepository.save(inventoryEntity.get());
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateReleaseStatus(Long inventoryId, Boolean isReleased) {
        Optional<InventoryEntity> inventoryEntity = inventoryRepository.findById(inventoryId);
        if(inventoryEntity.isPresent()){
            inventoryEntity.get().setIsReleased(isReleased);
            inventoryRepository.save(inventoryEntity.get());
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateReOrderLevel(Long inventoryId, Integer reorderLevel) {
        Optional<InventoryEntity> inventoryEntity = inventoryRepository.findById(inventoryId);
        if(inventoryEntity.isPresent()){
            inventoryEntity.get().setReorderLevel(reorderLevel);
            inventoryRepository.save(inventoryEntity.get());
            return true;
        }
        return false;
    }

    @Override
    public List<InventoryDto> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toDto)
                .toList();
    }

    @Override
    public Optional<InventoryDto> getInventoryById(Long id) {
        return inventoryRepository.findById(id).map(inventoryMapper::toDto);
    }

    @Override
    public List<SubInventoryDto> getSubInventoryById(Long inventoryId) {
        return subInventoryRepository.findByInventory_Id(inventoryId)
                .stream()
                .map(subInventoryMapper::toDto)
                .toList();
    }

    @Override
    public ResponseDto<SubInventoryDto> updateSubInventoryById(SubInventoryDto dto) {
        Optional<SubInventoryEntity> subInventoryEntity = subInventoryRepository
                .findById(
                        new SubInventoryId(dto.inventoryId(), dto.colorId(), dto.sizeId())
                );

        if (subInventoryEntity.isPresent()) {
            SubInventoryEntity subInventory = subInventoryEntity.get();
            subInventory.setQuantity(dto.quantity());
            subInventory.setIsSoldOut(dto.isSoldOut());
            subInventory.setColor(colorRepository.findById(dto.colorId()).orElse(null));
            subInventory.setColorId(dto.colorId());
            subInventory.setSize(sizeRepository.findById(dto.sizeId()).orElse(null));
            subInventory.setSizeId(dto.sizeId());
            subInventoryRepository.save(subInventory);
            return ResponseDto.success(subInventoryMapper.toDto(subInventory), "SubInventory updated successfully");
        }

        return ResponseDto.error("SubInventory not found");
    }
}