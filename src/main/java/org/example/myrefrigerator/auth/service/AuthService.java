package org.example.myrefrigerator.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.auth.redis.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void logout(Long userId) {

        refreshTokenRepository.deleteById(userId);
    }
}