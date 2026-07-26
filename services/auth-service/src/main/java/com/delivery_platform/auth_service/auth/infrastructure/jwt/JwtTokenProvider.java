package com.delivery_platform.auth_service.auth.infrastructure.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties properties;

    public String generateAccessToken(UUID userId, String role) {
    }

    public String generateRefreshToken(UUID userId) {
    }

    public UUID getUserId(String token) {
    }

    public String getRole(String token) {
    }

    public JwtType getType(String token) {
    }

    public boolean validate(String token) {
    }

}
