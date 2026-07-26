package com.delivery_platform.auth_service.auth.application.command;

public record ReissueTokenCommand(
        String refreshToken
) {
}
