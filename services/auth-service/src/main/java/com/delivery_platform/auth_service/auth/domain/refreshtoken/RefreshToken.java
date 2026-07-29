package com.delivery_platform.auth_service.auth.domain.refreshtoken;

import com.delivery_platform.auth_service.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "p_refresh_token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private boolean revoked;

    private RefreshToken(
            UUID userId,
            String token,
            Instant expiresAt
    ) {
        this.userId = userId;
        this.token = token;
        this.expiresAt = expiresAt;
        this.revoked = false;
    }

    public static RefreshToken create(
            UUID userId,
            String token,
            Instant expiresAt
    ) {
        return new RefreshToken(
                userId,
                token,
                expiresAt
        );
    }

    // 토큰 만료 여부
    public boolean isExpired() {
        return expiresAt.isBefore(Instant.now());
    }

    // 토큰 재발급
    public void renew(
            String token,
            Instant expiresAt
    ) {
        this.token = token;
        this.expiresAt = expiresAt;
    }

    // 로그아웃
    public void revoke() {
        this.revoked = true;
    }

    // 사용 가능한 토큰 여부
    public boolean isAvailable() {
        return !revoked && !isExpired();
    }
}
