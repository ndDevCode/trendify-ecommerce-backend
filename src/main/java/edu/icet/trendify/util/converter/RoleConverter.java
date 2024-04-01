package edu.icet.trendify.util.converter;

import edu.icet.trendify.util.enums.Role;
import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {

        return switch (role){
            case ADMIN -> 1;
            case OWNER -> 2;
        };
    }

    @Override
    public Role convertToEntityAttribute(Integer integer) {

        return switch (integer){
            case 1 -> Role.ADMIN;
            case 2 -> Role.OWNER;
            default -> null;
        };
    }
}
