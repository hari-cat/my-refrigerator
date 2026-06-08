package org.example.myrefrigerator.auth.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {
    private final User user;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(user.getRole().name())
        );
    }

    @Override
    public String getName() {
        return user.getId().toString();
    }

    public Long getId(){
        return user.getId();
    }

    public String getEmail(){
        return user.getEmail();
    }
}
