package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.product.ProductDto;
import edu.icet.trendify.dto.product.ProductReviewDto;
import edu.icet.trendify.entity.product.ProductEntity;
import edu.icet.trendify.repository.product.ProductRepository;
import edu.icet.trendify.repository.product.ProductReviewRepository;
import edu.icet.trendify.service.ProductService;
import edu.icet.trendify.util.mapper.ProductMapper;
import edu.icet.trendify.util.mapper.ProductReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductReviewRepository productReviewRepository;

    private final ProductMapper productMapper;
    private final ProductReviewMapper productReviewMapper;

    @Override
    public ResponseDto<ProductDto> saveProduct(ProductDto productDto) {
        try{
            ProductEntity savedProduct = productRepository.save(productMapper.toEntity(productDto));
            return ResponseDto.success(
                    productMapper.toDto(savedProduct),
                    "Product saved successfully!"
            );
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseDto.error("Error while saving product!");
        }
    }

    @Override
    public ResponseDto<ProductDto> updateProduct(ProductDto productDto) {
        try{
            ProductEntity existingProduct = productRepository.findById(productDto.id()).orElse(null);
            if(existingProduct == null){
                return ResponseDto.error("Product not found!");
            }

            ProductEntity savedProduct = productRepository.save(productMapper.toEntity(productDto));
            return ResponseDto.success(productMapper.toDto(savedProduct),"Product updated successfully!");

        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseDto.error("Error while updating product!");
        }
    }

    @Override
    public Optional<ProductDto> getProductById(Integer id) {
        return productRepository.findById(id).map(productMapper::toDto);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    @Override
    public Boolean addProductReview(ProductReviewDto reviewDto) {
        try{
            productReviewRepository.save(productReviewMapper.toEntity(reviewDto));
            return Boolean.TRUE;
        } catch (Exception e){
            log.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updateProductReview(ProductReviewDto reviewDto) {
        try {
            if(!productReviewRepository.existsByProductIdAndCusId(reviewDto.productId(), reviewDto.cusId())){
                return Boolean.FALSE;
            }
            productReviewRepository.save(productReviewMapper.toEntity(reviewDto));
            return Boolean.TRUE;
        } catch (Exception e){
            log.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    @Override
    public List<ProductReviewDto> getProductReviewByProductId(Long id) {
        return productReviewRepository
                .findByProductId(id)
                .stream()
                .map(productReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductReviewDto> getProductReviewByCustomerId(Long id) {
        return productReviewRepository
                .findByCusId(id)
                .stream()
                .map(productReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByRating(Short rating) {
        return productRepository
                .findByRating(rating)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }
}
