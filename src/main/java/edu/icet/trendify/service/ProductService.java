package edu.icet.trendify.service;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.product.ProductDto;
import edu.icet.trendify.dto.product.ProductReviewDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ResponseDto<ProductDto> saveProduct(ProductDto productDto);
    ResponseDto<ProductDto> updateProduct(ProductDto productDto);
    Optional<ProductDto> getProductById(Integer id);
    List<ProductDto> getAllProduct();

    Boolean addProductReview(ProductReviewDto reviewDto);
    Boolean updateProductReview(ProductReviewDto reviewDto);
    List<ProductReviewDto> getProductReviewByProductId(Long id);
    List<ProductReviewDto> getProductReviewByCustomerId(Long id);
    List<ProductDto> getProductByRating(Short rating);
}
