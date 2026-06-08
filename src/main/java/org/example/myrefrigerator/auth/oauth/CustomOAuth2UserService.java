package org.example.myrefrigerator.auth.oauth;

import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.user.entity.Provider;
import org.example.myrefrigerator.user.entity.User;
import org.example.myrefrigerator.user.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        OAuthAttributes attributes = OAuthAttributes.ofGoogle(oAuth2User.getAttributes());

        User user = userRepository.findByProviderAndProviderId(Provider.GOOGLE, attributes.getProviderId()).orElseGet(() -> userRepository.save(attributes.toEntity()));

        return new CustomOAuth2User(user);
    }
}
