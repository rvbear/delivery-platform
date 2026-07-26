package com.delivery_platform.auth_service.auth.application;

import com.delivery_platform.auth_service.auth.application.command.LoginCommand;
import com.delivery_platform.auth_service.auth.application.command.LogoutCommand;
import com.delivery_platform.auth_service.auth.application.command.ReissueTokenCommand;
import com.delivery_platform.auth_service.auth.application.result.LoginResult;
import com.delivery_platform.auth_service.auth.application.result.TokenResult;
import com.delivery_platform.auth_service.auth.domain.refreshtoken.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public LoginResult login(LoginCommand command) {

        // TODO
        // 1. User Service에서 사용자 조회
        // 2. 비밀번호 검증
        // 3. Access Token 생성
        // 4. Refresh Token 생성
        // 5. Refresh Token 저장
        // 6. LoginResult 반환

        return null;
    }

    @Transactional
    public void logout(LogoutCommand command) {

        // TODO
        // Refresh Token 폐기

    }

    @Transactional
    public TokenResult reissue(ReissueTokenCommand command) {

        // TODO
        // 1. Refresh Token 검증
        // 2. Access Token 재발급
        // 3. Refresh Token Rotation
        // 4. TokenResult 반환

        return null;
    }
}
