package edu.icet.trendify.entity.user;

import edu.icet.trendify.util.converter.RoleConverter;
import edu.icet.trendify.util.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

@Entity
@Table(name = "admin")
public class AdminEntity extends User {
    @Convert(converter = RoleConverter.class)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String contact;
}
