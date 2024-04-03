package edu.icet.trendify.entity.user;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "refresh_token")
public class RefreshTokenEntity {
    @Id
    private String refreshToken;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;
}
