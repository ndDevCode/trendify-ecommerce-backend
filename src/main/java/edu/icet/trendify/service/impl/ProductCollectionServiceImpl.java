package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.product.CollectionDto;
import edu.icet.trendify.dto.product.ProductDto;
import edu.icet.trendify.entity.product.CollectionEntity;
import edu.icet.trendify.entity.product.ProductCollectionEntity;
import edu.icet.trendify.repository.product.CollectionRepository;
import edu.icet.trendify.repository.product.ProductCollectionRepository;
import edu.icet.trendify.repository.product.ProductRepository;
import edu.icet.trendify.service.ProductCollectionService;
import edu.icet.trendify.util.mapper.CollectionMapper;
import edu.icet.trendify.util.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCollectionServiceImpl implements ProductCollectionService {

    private final CollectionRepository collectionRepository;
    private final ProductCollectionRepository productCollectionRepository;
    private final ProductRepository productRepository;

    private final CollectionMapper collectionMapper;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ResponseDto<CollectionDto> createCollection(CollectionDto collectionDto) {
        try {

            CollectionEntity savedCollection = collectionRepository.save(collectionMapper.toEntity(collectionDto));

            collectionDto.productList().forEach(productDto ->
                    productCollectionRepository.save(
                            new ProductCollectionEntity(
                                    productDto.id(),
                                    savedCollection.getId(),
                                    savedCollection,
                                    productMapper.toEntity(productDto)
                            )
                    )
            );

            return ResponseDto.success(
                    collectionMapper.toDto(savedCollection),
                    "Collection created successfully!"
            );

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.error("Error while creating collection!");
        }
    }

    @Override
    @Transactional
    public ResponseDto<CollectionDto> updateCollection(CollectionDto collectionDto) {
        if (!collectionRepository.existsById(collectionDto.id())) {
            return ResponseDto.error("Collection not found!");
        }

        try {
            CollectionEntity savedCollection = collectionRepository.save(collectionMapper.toEntity(collectionDto));

            List<ProductCollectionEntity> existingProducts = productCollectionRepository.findByCollectionId(collectionDto.id());
            List<ProductCollectionEntity> updatedProducts = collectionDto.productList()
                    .stream()
                    .map(productDto -> new ProductCollectionEntity(
                            productDto.id(),
                            savedCollection.getId(),
                            savedCollection,
                            productMapper.toEntity(productDto)
                    )).toList();

            List<ProductCollectionEntity> removedProducts = existingProducts
                    .stream()
                    .filter(product ->
                            updatedProducts
                                    .stream()
                                    .noneMatch(
                                            updatedProduct ->
                                                    updatedProduct
                                                            .getProductId().equals(product.getProductId())
                                    )
                            )
                    .toList();

            productCollectionRepository.saveAll(updatedProducts);
            productCollectionRepository.deleteAll(removedProducts);

            return ResponseDto.success(
                    collectionMapper.toDto(savedCollection),
                    "Collection updated successfully!"
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.error("Error while updating collection!");
        }
    }

    @Override
    public List<CollectionDto> getAllCollection() {
        List<CollectionEntity> collectionEntityList = collectionRepository.findAll();
        return collectionEntityList.stream().map(collection ->
            new CollectionDto(
                    collection.getId(),
                    collection.getName(),
                    collection.getDiscountRate(),
                    collection.getCreatedAt(),
                    collection.getIsActive(),
                    productRepository.findByProductCollectionList_CollectionId(collection.getId())
                            .stream()
                            .map(productMapper::toDto)
                            .toList()
            )
        ).toList();
    }

    @Override
    public List<ProductDto> getProductByCollectionId(Integer id) {
        return productRepository.findByProductCollectionList_CollectionId(id)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<CollectionDto> getCollectionByProductId(Integer id) {
        return collectionRepository.findByProductCollectionList_ProductId(id)
                .stream()
                .map(collectionMapper::toDto)
                .toList();
    }
}
