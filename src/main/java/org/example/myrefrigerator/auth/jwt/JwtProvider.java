package org.example.myrefrigerator.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey secretKey;

    @PostConstruct
    public void init(){
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    // Jwt token 생성
    public String createAccessToken(Long userId, String role){
        Date now = new Date();

        Date expired = new Date(now.getTime() + 1000L * 60 * 30);

        return Jwts.builder()
                .subject(userId.toString())
                .claim("role", role)
                .issuedAt(now)
                .expiration(expired)
                .signWith(secretKey)
                .compact();
    }
    // Jwt refresh token 발급
    public String createRefreshKey(Long userId){
        Date now = new Date();

        Date expired = new Date(
                now.getTime() + 1000L * 60 * 60 * 24 * 1
        );

        return Jwts.builder()
                .subject(userId.toString())
                .issuedAt(now)
                .expiration(expired)
                .signWith(secretKey)
                .compact();
    }
    // Jwt token 유효성 검증
    public boolean validateToken(String token){
       try {
           Jwts.parser()
                   .verifyWith(secretKey)
                   .build()
                   .parseSignedClaims(token);

           return true;
       } catch (Exception e){
           return false;
       }
    }
    // userId 얻기
    public Long getUserId(
            String token
    ){
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }
    // role 얻기
    public String getRole(
            String token
    ){
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("claims", String.class);
    }
}
