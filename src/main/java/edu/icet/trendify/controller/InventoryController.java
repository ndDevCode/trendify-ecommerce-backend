package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.inventory.InventoryDto;
import edu.icet.trendify.dto.inventory.SubInventoryDto;
import edu.icet.trendify.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<InventoryDto>> createInventory(@RequestBody @Valid InventoryDto inventoryDto) {
        ResponseDto<InventoryDto> inventory = inventoryService.createInventory(inventoryDto);
        if (Boolean.TRUE.equals(inventory.getIsSuccess())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(inventory);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(inventory);
    }

    @PutMapping("")
    public ResponseEntity<ResponseDto<InventoryDto>> updateInventory(@RequestBody @Valid InventoryDto inventoryDto) {
        ResponseDto<InventoryDto> inventory = inventoryService.updateInventory(inventoryDto);
        if (Boolean.TRUE.equals(inventory.getIsSuccess())) {
            return ResponseEntity.status(HttpStatus.OK).body(inventory);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(inventory);
    }

    @PutMapping("/remark")
    public ResponseEntity<ResponseDto<Boolean>> updateInventoryRemark(@RequestParam Long inventoryId, @RequestParam String remark) {
        Boolean inventory = inventoryService.updateInventoryRemark(inventoryId, remark);
        if (Boolean.TRUE.equals(inventory)) {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(null, "Remark updated successfully!"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseDto.error("Failed to update remark!"));
    }

    @PutMapping("/release")
    public ResponseEntity<ResponseDto<Boolean>> updateReleaseStatus(@RequestParam Long inventoryId, @RequestParam Boolean isReleased) {
        Boolean inventory = inventoryService.updateReleaseStatus(inventoryId, isReleased);
        if (Boolean.TRUE.equals(inventory)) {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(null, "Release status updated successfully!"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseDto.error("Failed to update release status!"));
    }

    @PutMapping("/rol")
    public ResponseEntity<ResponseDto<Boolean>> updateReOrderLevel(@RequestParam Long inventoryId, @RequestParam Integer reorderLevel) {
        Boolean inventory = inventoryService.updateReOrderLevel(inventoryId, reorderLevel);
        if (Boolean.TRUE.equals(inventory)) {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(null, "Reorder level updated successfully!"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseDto.error("Failed to update reorder level!"));
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<InventoryDto>>> getAllInventory() {
        List<InventoryDto> inventory = inventoryService.getAllInventory();
        if (inventory != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(inventory, "Inventory fetched successfully!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDto.error("No inventory found!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<InventoryDto>> getInventoryById(@PathVariable Long id) {
        Optional<InventoryDto> inventoryById = inventoryService.getInventoryById(id);
        return inventoryById
                .map(inventoryDto -> ResponseEntity.status(HttpStatus.OK)
                        .body(ResponseDto.success(inventoryDto, "Inventory found!")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseDto.error("Inventory not found!"))
                );
    }

    @GetMapping("/sub/{id}")
    public ResponseEntity<ResponseDto<List<SubInventoryDto>>> getSubInventoryById(@PathVariable Long id) {
        List<SubInventoryDto> subInventory = inventoryService.getSubInventoryById(id);
        if (subInventory != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.success(subInventory, "Sub inventory fetched successfully!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDto.error("No sub inventory found!"));
    }

    @PutMapping("/sub")
    public ResponseEntity<ResponseDto<SubInventoryDto>> updateSubInventoryById(@RequestBody @Valid SubInventoryDto subInventoryDto) {
        ResponseDto<SubInventoryDto> subInventory = inventoryService.updateSubInventoryById(subInventoryDto);
        if (Boolean.TRUE.equals(subInventory.getIsSuccess())) {
            return ResponseEntity.status(HttpStatus.OK).body(subInventory);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(subInventory);
    }
}
