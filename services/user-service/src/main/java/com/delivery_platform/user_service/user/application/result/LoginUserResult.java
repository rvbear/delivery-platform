package com.delivery_platform.user_service.user.application.result;

import java.util.UUID;

public record LoginUserResult(
        UUID userId,
        String password,
        String role
) {
}
