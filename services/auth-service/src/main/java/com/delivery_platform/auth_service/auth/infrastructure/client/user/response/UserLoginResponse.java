package com.delivery_platform.auth_service.auth.infrastructure.client.user.response;

import java.util.UUID;

public record UserLoginResponse(
        UUID userId,
        String password,
        String role
) {
}
