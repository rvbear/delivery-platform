package com.delivery_platform.user_service.user.presentation.controller;

import com.delivery_platform.user_service.user.application.UserService;
import com.delivery_platform.user_service.user.application.result.LoginUserResult;
import com.delivery_platform.user_service.user.presentation.request.UserLoginRequest;
import com.delivery_platform.user_service.user.presentation.response.UserLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/users")
public class InternalUserController {

    private final UserService userService;

    @PostMapping("/login")
    public UserLoginResponse login(
            @RequestBody UserLoginRequest request
    ) {

        LoginUserResult result =
                userService.getLoginUser(request.username());

        return new UserLoginResponse(
                result.userId(),
                result.password(),
                result.role()
        );
    }

}
