package org.example.myrefrigerator.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.auth.jwt.JwtProvider;
import org.example.myrefrigerator.auth.oauth.CustomOAuth2User;
import org.example.myrefrigerator.auth.redis.RefreshToken;
import org.example.myrefrigerator.auth.redis.RefreshTokenRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        CustomOAuth2User principal =
                (CustomOAuth2User)
                        authentication.getPrincipal();

        Long userId =
                principal.getId();

        String role =
                principal.getUser()
                        .getRole()
                        .name();

        String accessToken =
                jwtProvider.createAccessToken(
                        userId,
                        role
                );
        String refreshToken =
                jwtProvider.createRefreshKey(
                        userId
                );

        refreshTokenRepository.save(
                new RefreshToken(
                        userId,
                        refreshToken,
                        60L * 60 * 24 * 14
                )
        );

        Cookie accessCookie =
                new Cookie(
                        "accessToken",
                        accessToken
                );

        accessCookie.setHttpOnly(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(60 * 30);

        response.addCookie(accessCookie);

        Cookie refreshCookie =
                new Cookie(
                        "refreshToken",
                        refreshToken
                );

        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(
                60 * 60 * 24 * 14
        );

        response.addCookie(refreshCookie);

        response.setContentType("application/json");

        response.sendRedirect(
                "http://localhost:3000/refrigerator"
        );
    }
}
