package org.example.myrefrigerator.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.auth.oauth.CustomOAuth2User;
import org.example.myrefrigerator.user.entity.User;
import org.example.myrefrigerator.user.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (bearer == null || !bearer.startsWith("Bearer ")) {
            filterChain.doFilter(
                    request, response
            );

            return;
        }
        String token = bearer.substring(7);

        if (!jwtProvider.validateToken(token)) {
            filterChain.doFilter(request,response);
            return;
        }

        Long userId = jwtProvider.getUserId(token);

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = createAuthentication(user);

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private Authentication createAuthentication(User user){
        CustomOAuth2User principal = new CustomOAuth2User(user);

        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
    }
}
