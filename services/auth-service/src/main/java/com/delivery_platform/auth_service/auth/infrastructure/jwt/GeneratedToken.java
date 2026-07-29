package com.delivery_platform.auth_service.auth.infrastructure.jwt;

import java.time.Instant;

public record GeneratedToken(
        String token,
        Instant expiredAt
) {
    public static GeneratedToken create(String token, Instant expiredAt) {
        return new GeneratedToken(token, expiredAt);
    }
}
