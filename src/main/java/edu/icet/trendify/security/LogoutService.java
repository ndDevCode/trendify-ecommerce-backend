package edu.icet.trendify.security;

import edu.icet.trendify.repository.user.JwtTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final JwtTokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwtToken = authHeader.substring(7);
        var storedToken = tokenRepository.findByToken(jwtToken).orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();

            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
