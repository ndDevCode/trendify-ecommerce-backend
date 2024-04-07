package edu.icet.trendify.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "jwt_token")
public class JwtTokenEntity {
    @Id
    private String token;
    private boolean isExpired;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
