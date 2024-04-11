package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.product.ProductDto;
import edu.icet.trendify.dto.product.ProductReviewDto;
import edu.icet.trendify.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<ProductDto>> saveProduct(@RequestBody @Valid ProductDto productDto) {
        ResponseDto<ProductDto> productDtoResponseDto = productService.saveProduct(productDto);
        return Boolean.TRUE.equals(productDtoResponseDto.getIsSuccess()) ?
                ResponseEntity.status(HttpStatus.CREATED).body(productDtoResponseDto) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productDtoResponseDto);
    }

    @PutMapping("")
    public ResponseEntity<ResponseDto<ProductDto>> updateProduct(@RequestBody @Valid ProductDto productDto) {
        ResponseDto<ProductDto> productDtoResponseDto = productService.updateProduct(productDto);
        return Boolean.TRUE.equals(productDtoResponseDto.getIsSuccess()) ?
                ResponseEntity.ok(productDtoResponseDto) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productDtoResponseDto);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ResponseDto<ProductDto>> getProductById(@PathVariable("product-id") Integer id) {
        Optional<ProductDto> productById = productService.getProductById(id);
        return productById
                .map(productDto -> ResponseEntity.status(HttpStatus.OK)
                        .body(ResponseDto.success(productDto, "Product found!")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseDto.error("Product not found!"))
                );
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto<List<ProductDto>>> getAllProduct() {
        List<ProductDto> allProduct = productService.getAllProduct();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.success(allProduct, "Products found!"));
    }

    @PostMapping("/reviews")
    public ResponseEntity<ResponseDto<Boolean>> addProductReview(@RequestBody @Valid ProductReviewDto reviewDto) {
        if (Boolean.TRUE.equals(productService.addProductReview(reviewDto))) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseDto.success(true, "Review added successfully!"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.error("Failed to add review!"));
    }

    @PutMapping("/reviews")
    public ResponseEntity<ResponseDto<Boolean>> updateProductReview(@RequestBody @Valid ProductReviewDto reviewDto) {
        if (Boolean.TRUE.equals(productService.updateProductReview(reviewDto))) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success(true, "Review updated successfully!"));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDto.error("Failed to update review!"));
    }

    @GetMapping("/reviews")
    public ResponseEntity<ResponseDto<List<ProductReviewDto>>> getProductReviewByProductIdOrCustomerId(
            @RequestParam(value = "product-id", required = false) Integer id,
            @RequestParam(value = "customer-id", required = false) Long customerId
    ) {
        if (id != null) {
            List<ProductReviewDto> productReviewByProductId =
                    productService.getProductReviewByProductId(id);
            if (!productReviewByProductId.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(ResponseDto.success(productReviewByProductId, "Reviews found!"));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDto.error("Reviews not found by product id: " + id));
        } else if (customerId != null) {
            List<ProductReviewDto> productReviewByCustomerId =
                    productService.getProductReviewByCustomerId(customerId);
            if (!productReviewByCustomerId.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(ResponseDto.success(productReviewByCustomerId, "Reviews found!"));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDto.error("Reviews not found by customer id: " + customerId));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.error("Please provide product id or customer id!"));
        }
    }

    @GetMapping("/ratings/{rating}")
    public ResponseEntity<ResponseDto<List<ProductDto>>> getProductByRating(@PathVariable("rating") Short rating) {
        List<ProductDto> productByRating = productService.getProductByRating(rating);
        if (!productByRating.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success(productByRating, "Products found!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDto.error("Products not found!"));
    }
}
