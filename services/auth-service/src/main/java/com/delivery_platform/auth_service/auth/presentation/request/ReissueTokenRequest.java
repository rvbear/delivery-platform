package com.delivery_platform.auth_service.auth.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record ReissueTokenRequest(

        @NotBlank(message = "Refresh Token은 필수입니다.")
        String refreshToken

) {
}
