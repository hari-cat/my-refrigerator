package org.example.myrefrigerator.auth.oauth;

import lombok.Builder;
import lombok.Getter;
import org.example.myrefrigerator.user.entity.Provider;
import org.example.myrefrigerator.user.entity.Role;
import org.example.myrefrigerator.user.entity.User;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private String email;
    private String name;
    private String providerId;

    public static OAuthAttributes ofGoogle(Map<String, Object> attribute) {
        return OAuthAttributes.builder()
                .email((String) attribute.get("email"))
                .name((String) attribute.get("name"))
                .providerId((String) attribute.get("sub"))
                .build();

    }

    public User toEntity(){
        return User.builder()
                .email(email)
                .nickname(name)
                .provider(Provider.GOOGLE)
                .providerId(providerId)
                .role(Role.ROLE_USER)
                .build();
    }
}
