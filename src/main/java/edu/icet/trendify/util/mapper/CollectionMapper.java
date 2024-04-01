package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.product.CollectionDto;
import edu.icet.trendify.entity.product.CollectionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CollectionMapper {
    CollectionEntity toEntity(CollectionDto collectionDto);

    CollectionDto toDto(CollectionEntity collectionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CollectionEntity partialUpdate(CollectionDto collectionDto, @MappingTarget CollectionEntity collectionEntity);
}