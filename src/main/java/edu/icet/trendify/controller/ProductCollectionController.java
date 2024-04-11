package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.product.CollectionDto;
import edu.icet.trendify.dto.product.ProductDto;
import edu.icet.trendify.service.ProductCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-collections")
@RequiredArgsConstructor
public class ProductCollectionController {

    private final ProductCollectionService productCollectionService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<CollectionDto>> createCollection(@RequestBody CollectionDto collectionDto) {
        ResponseDto<CollectionDto> collection = productCollectionService.createCollection(collectionDto);
        return Boolean.TRUE.equals(collection.getIsSuccess()) ?
                    ResponseEntity.ok(collection) :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(collection);
    }

    @PutMapping("")
    public ResponseEntity<ResponseDto<CollectionDto>> updateCollection(@RequestBody CollectionDto collectionDto) {
        ResponseDto<CollectionDto> collection = productCollectionService.updateCollection(collectionDto);
        return Boolean.TRUE.equals(collection.getIsSuccess()) ?
                    ResponseEntity.ok(collection) :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(collection);
    }

    @GetMapping("")
    public ResponseEntity<List<CollectionDto>> getAllCollection() {
        return new ResponseEntity<>(productCollectionService.getAllCollection(),HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<List<ProductDto>> getProductByCollectionId(@PathVariable Integer id) {
        return new ResponseEntity<>(productCollectionService.getProductByCollectionId(id),HttpStatus.OK);
    }

    @GetMapping("/collections/{id}")
    public ResponseEntity<List<CollectionDto>> getCollectionByProductId(@PathVariable Integer id) {
        return new ResponseEntity<>(productCollectionService.getCollectionByProductId(id),HttpStatus.OK);
    }
}
