package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.billing.BillingInfoDto;
import edu.icet.trendify.entity.billing.BillingInfoEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BillingInfoMapper {
    BillingInfoEntity toEntity(BillingInfoDto billingInfoDto);

    BillingInfoDto toDto(BillingInfoEntity billingInfoEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BillingInfoEntity partialUpdate(BillingInfoDto billingInfoDto, @MappingTarget BillingInfoEntity billingInfoEntity);
}