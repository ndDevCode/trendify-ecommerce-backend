package edu.icet.trendify.service;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.product.CollectionDto;
import edu.icet.trendify.dto.product.ProductDto;

import java.util.List;

public interface ProductCollectionService {
    ResponseDto<CollectionDto> createCollection(CollectionDto collectionDto);
    ResponseDto<CollectionDto> updateCollection(CollectionDto collectionDto);
    List<CollectionDto> getAllCollection();
    List<ProductDto> getProductByCollectionId(Integer id);
    List<CollectionDto> getCollectionByProductId(Integer id);
}
