package edu.icet.trendify.dto.user;

import edu.icet.trendify.entity.user.UserSuper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDto <T> {

    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer ";
    private T user;
}
