package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.user.ContactDto;
import edu.icet.trendify.entity.user.ContactEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactMapper {
    ContactEntity toEntity(ContactDto contactDto);

    ContactDto toDto(ContactEntity contactEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContactEntity partialUpdate(ContactDto contactDto, @MappingTarget ContactEntity contactEntity);
}