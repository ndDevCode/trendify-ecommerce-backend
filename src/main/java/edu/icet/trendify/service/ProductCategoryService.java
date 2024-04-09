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
    List<SubCategoryDto> getSubCategoryByMainCategory(Integer id);
    List<MainCategoryDto> getMainCategoryBySubCategory(Integer id);
    Boolean removeSubCategoriesFromMainCategory(Integer subCategoryId,Integer mainCategoryId);
    Boolean removeMainCategory(Integer id);
    Boolean removeSubCategory(Integer id);
    Boolean addSubCategoriesToMainCategory(Integer id, List<Integer> subCategoryIds);
}
