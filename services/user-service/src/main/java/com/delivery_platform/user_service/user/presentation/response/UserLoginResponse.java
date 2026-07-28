package com.delivery_platform.user_service.user.presentation.response;

import java.util.UUID;

public record UserLoginResponse(
        UUID userId,
        String password,
        String role
) {
}
