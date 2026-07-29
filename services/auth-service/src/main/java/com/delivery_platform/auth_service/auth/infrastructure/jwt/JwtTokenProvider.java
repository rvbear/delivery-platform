package com.delivery_platform.auth_service.auth.infrastructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties properties;

    // SecretKey 객체로 변환
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(
                properties.getSecretKey().getBytes(StandardCharsets.UTF_8)
        );
    }

    // Access Token 생성
    public GeneratedToken generateAccessToken(UUID userId, String role) {
        Instant now = Instant.now();

        Instant expiration =
                now.plusMillis(properties.getAccessTokenExpiration());

        return GeneratedToken.create(
                Jwts.builder()
                        // 사용자 PK 저장
                        .subject(userId.toString())
                        // 사용자 권한 저장
                        .claim(
                                JwtClaimKeys.ROLE,
                                role
                        )
                        // Token Type 저장
                        .claim(
                                JwtClaimKeys.TYPE,
                                JwtType.ACCESS.name()
                        )
                        .issuedAt(Date.from(now))
                        .expiration(Date.from(expiration))
                        .signWith(getSecretKey())
                        .compact(),
                expiration
        );
    }

    // RefreshToken 생성
    public GeneratedToken generateRefreshToken(UUID userId) {
        Instant now = Instant.now();

        Instant expiration =
                now.plusMillis(properties.getRefreshTokenExpiration());

        return GeneratedToken.create(
                Jwts.builder()
                        // 사용자 PK
                        .subject(userId.toString())
                        // Token Type 저장
                        .claim(
                                JwtClaimKeys.TYPE,
                                JwtType.REFRESH.name()
                        )
                        .issuedAt(Date.from(now))
                        .expiration(Date.from(expiration))
                        .signWith(getSecretKey())
                        .compact(),
                expiration
        );
    }

    // JWT 검증
    public boolean validate(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // AccessToken 검증
    public boolean validateAccessToken(String token) {
        if (!validate(token)) {
            return false;
        }

        try {
            return getTokenType(token) == JwtType.ACCESS;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // RefreshToken 검증
    public boolean validateRefreshToken(String token) {
        if (!validate(token)) {
            return false;
        }

        try {
            return getTokenType(token) == JwtType.REFRESH;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // JWT 내부 Claim 정보 반환
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    // JWT에 저장된 사용자 PK 반환 (subject 사용)
    public UUID getUserId(String token) {
        return UUID.fromString(getClaims(token).getSubject());
    }

    // JWT에 저장된 사용자 Role 반환 (Custom Claim 사용)
    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // JWT에 저장된 Token Type 반환 (Custom Claim 사용)
    public JwtType getTokenType(String token){
        String type =
                getClaims(token)
                        .get("type", String.class);

        return JwtType.valueOf(type);
    }
}
