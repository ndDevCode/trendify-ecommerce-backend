package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.product.MainCategoryDto;
import edu.icet.trendify.dto.product.SubCategoryDto;
import edu.icet.trendify.service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping("/main")
    public ResponseEntity<ResponseDto<MainCategoryDto>> saveMainCategory(@RequestBody @Valid MainCategoryDto mainCategoryDto) {
        ResponseDto<MainCategoryDto> mainCategory = productCategoryService.saveMainCategory(mainCategoryDto);
        if(Boolean.TRUE.equals(mainCategory.getIsSuccess())){
            return ResponseEntity.status(HttpStatus.CREATED).body(mainCategory);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mainCategory);
    }

    @PutMapping("/main")
    public ResponseEntity<ResponseDto<MainCategoryDto>> updateMainCategory(@RequestBody @Valid MainCategoryDto mainCategoryDto) {
        ResponseDto<MainCategoryDto> mainCategory = productCategoryService.updateMainCategory(mainCategoryDto);
        if(Boolean.TRUE.equals(mainCategory.getIsSuccess())){
            return ResponseEntity.status(HttpStatus.OK).body(mainCategory);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mainCategory);
    }

    @PostMapping("/sub")
    public ResponseEntity<ResponseDto<SubCategoryDto>> saveSubCategory(@RequestBody @Valid SubCategoryDto subCategoryDto) {
        ResponseDto<SubCategoryDto> subCategory = productCategoryService.saveSubCategory(subCategoryDto);
        if(Boolean.TRUE.equals(subCategory.getIsSuccess())){
            return ResponseEntity.status(HttpStatus.CREATED).body(subCategory);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(subCategory);
    }

    @PutMapping("/sub")
    public ResponseEntity<ResponseDto<SubCategoryDto>> updateSubCategory(@RequestBody @Valid SubCategoryDto subCategoryDto) {
        ResponseDto<SubCategoryDto> subCategory = productCategoryService.updateSubCategory(subCategoryDto);
        if(Boolean.TRUE.equals(subCategory.getIsSuccess())){
            return ResponseEntity.status(HttpStatus.OK).body(subCategory);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(subCategory);
    }

    @GetMapping("/main")
    public ResponseEntity<ResponseDto<List<MainCategoryDto>>> getAllMainCategory() {
        List<MainCategoryDto> mainCategory = productCategoryService.getAllMainCategory();
        if(!mainCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success(mainCategory, "Main categories found!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDto.error("Main categories not found!"));
    }

    @GetMapping("/sub")
    public ResponseEntity<ResponseDto<List<SubCategoryDto>>> getAllSubcategory() {
        List<SubCategoryDto> subCategory = productCategoryService.getAllSubcategory();
        if(!subCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success(subCategory, "Sub categories found!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDto.error("Sub categories not found!"));
    }

    @GetMapping("/sub/{id}")
    public ResponseEntity<ResponseDto<List<SubCategoryDto>>> getSubCategoryByMainCategory(@PathVariable Integer id) {
        List<SubCategoryDto> subCategory = productCategoryService.getSubCategoryByMainCategory(id);
        if(!subCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success(subCategory, "Sub categories found!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDto.error("Sub categories not found!"));
    }

    @GetMapping("/main/{id}")
    public ResponseEntity<ResponseDto<List<MainCategoryDto>>> getMainCategoryBySubCategory(@PathVariable Integer id) {
        List<MainCategoryDto> mainCategory = productCategoryService.getMainCategoryBySubCategory(id);
        if(!mainCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success(mainCategory, "Main categories found!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDto.error("Main categories not found!"));
    }

    @DeleteMapping("/main/{id}")
    public ResponseEntity<ResponseDto<Boolean>> removeMainCategory(@PathVariable Integer id) {
        Boolean mainCategory = productCategoryService.removeMainCategory(id);
        if(Boolean.TRUE.equals(mainCategory)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success(null, "Main category deleted successfully!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.error("Main category not found by id: " + id));
    }

    @DeleteMapping("/sub/{id}")
    public ResponseEntity<ResponseDto<Boolean>> removeSubCategory(@PathVariable Integer id) {
        Boolean subCategory = productCategoryService.removeSubCategory(id);
        if(Boolean.TRUE.equals(subCategory)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success(subCategory, "Sub category deleted successfully!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.error("Sub category not found by id: " + id));
    }

    @PostMapping("/main/{id}")
    public ResponseEntity<ResponseDto<Boolean>> addSubCategoriesToMainCategory(
            @PathVariable Integer id, @RequestBody List<Integer> subCategoryIds
    ) {
        Boolean mainCategory = productCategoryService.addSubCategoriesToMainCategory(id, subCategoryIds);
        if(Boolean.TRUE.equals(mainCategory)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success(
                            mainCategory,
                            "Sub categories added to main category successfully!"
                    ));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.error("Main category not found by id: " + id));
    }

    @DeleteMapping("/main/sub")
    public ResponseEntity<ResponseDto<Boolean>> removeSubCategoriesFromMainCategory(
            @RequestParam(value = "subCategoryId") Integer subCategoryId,
            @RequestParam(value = "mainCategoryId") Integer mainCategoryId
    ) {
        Boolean mainCategory = productCategoryService.removeSubCategoriesFromMainCategory(subCategoryId, mainCategoryId);
        if(Boolean.TRUE.equals(mainCategory)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success(
                            mainCategory,
                            "Sub categories removed from main category successfully!"
                    ));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.error("Main category not found by id: " + mainCategoryId));
    }
}
