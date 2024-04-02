package edu.icet.trendify.entity.user;

import edu.icet.trendify.repository.user.RoleRepository;
import edu.icet.trendify.util.converter.RoleConverter;
import edu.icet.trendify.util.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Convert(converter = RoleConverter.class)
    @Column(nullable = false)
    private Role role;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id",nullable = false))
    @ToString.Exclude
    private List<UserEntity> user;
}
