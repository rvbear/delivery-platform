package com.delivery_platform.auth_service.auth.presentation.response;

public record TokenResponse(

        String accessToken,
        String refreshToken

) {
}
