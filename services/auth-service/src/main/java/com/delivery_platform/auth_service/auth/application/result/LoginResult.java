package com.delivery_platform.auth_service.auth.application.result;

import java.util.UUID;

public record LoginResult(
        UUID userId,
        String accessToken,
        String refreshToken
) {
}
