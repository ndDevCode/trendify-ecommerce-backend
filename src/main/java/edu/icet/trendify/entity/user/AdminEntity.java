package edu.icet.trendify.entity.user;

import edu.icet.trendify.util.enums.Role;
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
public class AdminEntity extends UserEntity{
    private Role role;
    private String contact;
}
