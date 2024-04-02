package edu.icet.trendify.util.mapper;

import edu.icet.trendify.dto.user.AdminDto;
import edu.icet.trendify.entity.user.AdminEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminMapper {
    AdminEntity toEntity(AdminDto adminDto);

    AdminDto toDto(AdminEntity adminEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdminEntity partialUpdate(AdminDto adminDto, @MappingTarget AdminEntity adminEntity);
}