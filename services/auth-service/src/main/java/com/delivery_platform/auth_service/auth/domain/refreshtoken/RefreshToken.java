package com.delivery_platform.auth_service.auth.domain.refreshtoken;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private boolean revoked;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private RefreshToken(
            UUID userId,
            String token,
            LocalDateTime expiresAt
    ) {
        this.userId = userId;
        this.token = token;
        this.expiresAt = expiresAt;
        this.revoked = false;
    }

    public static RefreshToken create(
            UUID userId,
            String token,
            LocalDateTime expiresAt
    ) {
        return new RefreshToken(
                userId,
                token,
                expiresAt
        );
    }

    // 토큰 만료 여부
    public boolean isExpired() {
        return expiresAt.isBefore(LocalDateTime.now());
    }

    // 토큰 재발급
    public void renew(
            String token,
            LocalDateTime expiresAt
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
