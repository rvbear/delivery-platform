package com.delivery_platform.auth_service.auth.infrastructure.client.user;

import com.delivery_platform.auth_service.auth.infrastructure.client.user.dto.UserLoginRequest;
import com.delivery_platform.auth_service.auth.infrastructure.client.user.response.UserLoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserClient {

    @PostMapping("/internal/users/login")
    UserLoginResponse login(
            @RequestBody UserLoginRequest request
    );
}
