package edu.icet.trendify.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean isActive;

    @ManyToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<RoleEntity> role;

    @OneToOne(mappedBy = "user")
    private CustomerEntity customer;

    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER)
    private AdminEntity admin;
}
