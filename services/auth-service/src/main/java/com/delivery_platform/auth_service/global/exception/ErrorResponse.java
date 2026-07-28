package com.delivery_platform.auth_service.global.exception;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(
        String message,
        String code,
        Map<String, Object> details,
        Instant timestamp
) {
    public static ErrorResponse from(
            ErrorCode errorCode,
            String message,
            Map<String, Object> details
    ) {
        return new ErrorResponse(
                message,
                errorCode == null ? null : errorCode.getCode(),
                details == null ? Map.of() : details,
                Instant.now()
        );
    }
}
