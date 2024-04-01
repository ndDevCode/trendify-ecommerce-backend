package edu.icet.trendify.service;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.product.MainCategoryDto;
import edu.icet.trendify.dto.product.SubCategoryDto;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {
    ResponseDto<MainCategoryDto> saveMainCategory(MainCategoryDto mainCategoryDto);
    ResponseDto<MainCategoryDto> updateMainCategory(MainCategoryDto mainCategoryDto);
    ResponseDto<SubCategoryDto> saveSubCategory(SubCategoryDto mainCategoryDto);
    ResponseDto<SubCategoryDto> updateSubCategory(SubCategoryDto mainCategoryDto);

    List<MainCategoryDto> getAllMainCategory();
    List<SubCategoryDto> getAllSubcategory();
    Optional<List<SubCategoryDto>> getSubCategoryByMainCategory(Integer id);
    Optional<List<MainCategoryDto>> getMainCategoryBySubCategory(Integer id);
}
