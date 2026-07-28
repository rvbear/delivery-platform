package com.delivery_platform.auth_service.global.response;

public record ApiResponse<T>(
        String message,
        String code,
        T data
) {
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, "SUCCESS", data);
    }
}
