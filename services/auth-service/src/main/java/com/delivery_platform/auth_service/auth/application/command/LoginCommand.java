package com.delivery_platform.auth_service.auth.application.command;

public record LoginCommand(
        String username,
        String password
) {
}
