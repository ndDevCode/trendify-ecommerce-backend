package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.user.CustomerDto;
import edu.icet.trendify.entity.user.CustomerEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    CustomerEntity toEntity(CustomerDto customerDto);

    CustomerDto toDto(CustomerEntity customerEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CustomerEntity partialUpdate(CustomerDto customerDto, @MappingTarget CustomerEntity customerEntity);
}