package com.delivery_platform.auth_service.auth.application.result;

public record TokenResult(
        String accessToken,
        String refreshToken
) {
}
