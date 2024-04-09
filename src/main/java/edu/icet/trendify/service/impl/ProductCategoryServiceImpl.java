package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.product.MainCategoryDto;
import edu.icet.trendify.dto.product.SubCategoryDto;
import edu.icet.trendify.entity.product.MainCategoryEntity;
import edu.icet.trendify.entity.product.SubCategoryEntity;
import edu.icet.trendify.repository.product.MainCategoryRepository;
import edu.icet.trendify.repository.product.SubCategoryRepository;
import edu.icet.trendify.service.ProductCategoryService;
import edu.icet.trendify.util.mapper.MainCategoryMapper;
import edu.icet.trendify.util.mapper.SubCategoryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final MainCategoryMapper mainCategoryMapper;
    private final SubCategoryMapper subCategoryMapper;

    @Override
    @Transactional
    public ResponseDto<MainCategoryDto> saveMainCategory(MainCategoryDto mainCategoryDto) {
        if (mainCategoryRepository.existsByName(mainCategoryDto.name())) {
            return ResponseDto.error("Main category already exists!");
        }

        try {
            saveAndUpdateMainCategory(mainCategoryDto);
            return ResponseDto.success(mainCategoryDto, "Main category saved successfully!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.error("Error while saving main category!");
        }
    }

    @Override
    @Transactional
    public ResponseDto<MainCategoryDto> updateMainCategory(MainCategoryDto mainCategoryDto) {
        if (mainCategoryRepository.existsById(mainCategoryDto.id())) {
            try {
                saveAndUpdateMainCategory(mainCategoryDto);
                return ResponseDto.success(mainCategoryDto, "Main category updated successfully!");
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseDto.error("Error while updating main category!");
            }
        }
        return ResponseDto.error("Main category not found by id: " + mainCategoryDto.id());
    }

    @Transactional
    protected void saveAndUpdateMainCategory(MainCategoryDto mainCategoryDto) {
        MainCategoryEntity mainCategoryEntity = mainCategoryMapper.toEntity(mainCategoryDto);
        mainCategoryEntity.setSubCategoryList(null);
        MainCategoryEntity savedEntity = mainCategoryRepository.save(mainCategoryEntity);

        List<SubCategoryEntity> subCategoryEntities = mainCategoryDto
                .subCategoryList()
                .stream()
                .map(subCategoryMapper::toEntity)
                .toList();

        for (SubCategoryEntity subCategoryEntity : subCategoryEntities) {
            subCategoryEntity.setMainCategoryList(List.of(savedEntity));
        }

        subCategoryRepository.saveAll(subCategoryEntities);
    }

    @Override
    @Transactional
    public ResponseDto<SubCategoryDto> saveSubCategory(SubCategoryDto subCategoryDto) {
        if (subCategoryRepository.existsByName(subCategoryDto.name())) {
            return ResponseDto.error("Sub category already exists!");
        }

        try {
            subCategoryRepository.save(subCategoryMapper.toEntity(subCategoryDto));
            return ResponseDto.success(subCategoryDto, "Sub category saved successfully!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.error("Error while saving sub category!");
        }
    }

    @Override
    @Transactional
    public ResponseDto<SubCategoryDto> updateSubCategory(SubCategoryDto subCategoryDto) {
        if (subCategoryRepository.existsById(subCategoryDto.id())) {
            try {
                subCategoryRepository.save(subCategoryMapper.toEntity(subCategoryDto));
                return ResponseDto.success(subCategoryDto, "Sub category updated successfully!");
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseDto.error("Error while updating sub category!");
            }
        }

        return ResponseDto.error("Sub category not found by id: " + subCategoryDto.id());
    }

    @Override
    public List<MainCategoryDto> getAllMainCategory() {
        return mainCategoryRepository.findAll().stream().map(mainCategoryMapper::toDto).toList();
    }

    @Override
    public List<SubCategoryDto> getAllSubcategory() {
        return subCategoryRepository.findAll().stream().map(subCategoryMapper::toDto).toList();
    }

    @Override
    public List<SubCategoryDto> getSubCategoryByMainCategory(Integer id) {
        return subCategoryRepository.findByMainCategoryId(id).stream().map(subCategoryMapper::toDto).toList();
    }

    @Override
    public List<MainCategoryDto> getMainCategoryBySubCategory(Integer id) {
        return mainCategoryRepository.findBySubCategoryId(id).stream().map(mainCategoryMapper::toDto).toList();
    }

    @Override
    public Boolean removeSubCategoriesFromMainCategory(Integer subCategoryId,Integer mainCategoryId) {
        if(!mainCategoryRepository.existsBySubAndMainCategoryIds(subCategoryId,mainCategoryId)) return false;
        mainCategoryRepository.deleteBySubCategoryId(subCategoryId,mainCategoryId);
        return true;
    }

    @Override
    public Boolean removeMainCategory(Integer id) {
        if(mainCategoryRepository.existsById(id)){
            mainCategoryRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean removeSubCategory(Integer id) {
        if(subCategoryRepository.existsById(id)){
            subCategoryRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean addSubCategoriesToMainCategory(Integer mainCategoryId, List<Integer> subCategoryIds) {
        if(!mainCategoryRepository.existsById(mainCategoryId)) return false;
        for (Integer subCategoryId : subCategoryIds) {
            mainCategoryRepository.saveMainSubCategory(subCategoryId, mainCategoryId);
        }
        return true;
    }
}
