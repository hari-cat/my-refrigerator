package org.example.myrefrigerator.auth.redis;

import org.springframework.data.annotation.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash("refreshToken")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    private Long userId;

    private String token;

    @TimeToLive
    private Long ttl;

    public RefreshToken(Long userId, String token, Long ttl){
        this.userId = userId;
        this.token = token;
        this.ttl = ttl;
    }
}
