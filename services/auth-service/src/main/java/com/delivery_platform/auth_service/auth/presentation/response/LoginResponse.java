package com.delivery_platform.auth_service.auth.presentation.response;

import java.util.UUID;

public record LoginResponse(

        UUID userId,
        String accessToken,
        String refreshToken
) {
}
