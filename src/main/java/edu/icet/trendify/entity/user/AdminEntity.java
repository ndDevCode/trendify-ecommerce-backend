package edu.icet.trendify.entity.user;

import edu.icet.trendify.util.converter.RoleConverter;
import edu.icet.trendify.util.enums.Role;
import jakarta.persistence.*;
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
public class AdminEntity extends UserSuper {
    @Column(nullable = false,unique = true)
    private String contact;

    @OneToOne
    private UserEntity user;
}
