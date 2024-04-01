package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.product.ProductReviewDto;
import edu.icet.trendify.entity.product.ProductReviewEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductReviewMapper {
    ProductReviewEntity toEntity(ProductReviewDto productReviewDto);

    ProductReviewDto toDto(ProductReviewEntity productReviewEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductReviewEntity partialUpdate(ProductReviewDto productReviewDto, @MappingTarget ProductReviewEntity productReviewEntity);
}