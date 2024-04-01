package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.user.CustomerAddressDto;
import edu.icet.trendify.entity.user.CustomerAddressEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerAddressMapper {
    CustomerAddressEntity toEntity(CustomerAddressDto customerAddressDto);

    CustomerAddressDto toDto(CustomerAddressEntity customerAddressEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CustomerAddressEntity partialUpdate(CustomerAddressDto customerAddressDto, @MappingTarget CustomerAddressEntity customerAddressEntity);
}