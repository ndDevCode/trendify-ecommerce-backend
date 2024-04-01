package edu.icet.trendify.service;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.product.CollectionDto;
import edu.icet.trendify.dto.product.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductCollectionService {
    ResponseDto<CollectionDto> createCollection(CollectionDto collectionDto);
    ResponseDto<CollectionDto> updateCollection(CollectionDto collectionDto);
    List<CollectionDto> getAllCollection();
    Optional<List<ProductDto>> getProductByCollectionId(Integer id);
    Optional<List<CollectionDto>> getCollectionByProductId(Integer id);
}
