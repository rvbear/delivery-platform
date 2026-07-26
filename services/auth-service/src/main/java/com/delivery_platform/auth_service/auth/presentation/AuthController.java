package com.delivery_platform.auth_service.auth.presentation;

import com.delivery_platform.auth_service.auth.application.AuthService;
import com.delivery_platform.auth_service.auth.application.command.LoginCommand;
import com.delivery_platform.auth_service.auth.application.command.LogoutCommand;
import com.delivery_platform.auth_service.auth.application.command.ReissueTokenCommand;
import com.delivery_platform.auth_service.auth.application.result.LoginResult;
import com.delivery_platform.auth_service.auth.application.result.TokenResult;
import com.delivery_platform.auth_service.auth.presentation.request.LoginRequest;
import com.delivery_platform.auth_service.auth.presentation.request.LogoutRequest;
import com.delivery_platform.auth_service.auth.presentation.request.ReissueTokenRequest;
import com.delivery_platform.auth_service.auth.presentation.response.LoginResponse;
import com.delivery_platform.auth_service.auth.presentation.response.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {

        LoginResult loginResult = authService.login(
                new LoginCommand(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        return new LoginResponse(
                loginResult.userId(),
                loginResult.accessToken(),
                loginResult.refreshToken()
        );
    }

    @PostMapping("/logout")
    public void logout(
            @Valid @RequestBody LogoutRequest logoutRequest
    ) {

        authService.logout(
                new LogoutCommand(
                        logoutRequest.refreshToken()
                )
        );
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(
            @Valid @RequestBody ReissueTokenRequest reissueTokenRequest
    ) {

        TokenResult tokenResult = authService.reissue(
                new ReissueTokenCommand(
                        reissueTokenRequest.refreshToken()
                )
        );

        return new TokenResponse(
                tokenResult.accessToken(),
                tokenResult.refreshToken()
        );
    }
}
