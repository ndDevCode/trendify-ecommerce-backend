package edu.icet.trendify.entity.user;

import edu.icet.trendify.entity.id.UserRoleId;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
public class UserRoleEntity {
    @Id
    private Long userId;
    @Id
    private Long roleId;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role")
    private RoleEntity role;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user")
    private UserEntity user;
}
