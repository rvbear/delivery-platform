package com.delivery_platform.auth_service.auth.application;

import com.delivery_platform.auth_service.auth.application.command.LoginCommand;
import com.delivery_platform.auth_service.auth.application.command.LogoutCommand;
import com.delivery_platform.auth_service.auth.application.command.ReissueTokenCommand;
import com.delivery_platform.auth_service.auth.application.result.LoginResult;
import com.delivery_platform.auth_service.auth.application.result.TokenResult;
import com.delivery_platform.auth_service.auth.domain.refreshtoken.RefreshToken;
import com.delivery_platform.auth_service.auth.domain.refreshtoken.RefreshTokenRepository;
import com.delivery_platform.auth_service.auth.infrastructure.client.user.UserClient;
import com.delivery_platform.auth_service.auth.infrastructure.client.user.dto.UserLoginRequest;
import com.delivery_platform.auth_service.auth.infrastructure.client.user.response.UserLoginResponse;
import com.delivery_platform.auth_service.auth.infrastructure.jwt.GeneratedToken;
import com.delivery_platform.auth_service.auth.infrastructure.jwt.JwtTokenProvider;
import com.delivery_platform.auth_service.global.exception.BusinessException;
import com.delivery_platform.auth_service.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    @Transactional
    public LoginResult login(LoginCommand command) {

        // 1. 사용자 조회
        UserLoginResponse user = userClient.login(
                new UserLoginRequest(command.username())
        );

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(
                command.password(),
                user.password()
        )) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }

        // 3. Access Token 생성
        GeneratedToken accessToken =
                jwtTokenProvider.generateAccessToken(
                        user.userId(),
                        user.role()
                );

        // 4. Refresh Token 생성
        GeneratedToken refreshToken =
                jwtTokenProvider.generateRefreshToken(
                        user.userId()
                );

        // 5. Refresh Token 저장
        RefreshToken token = RefreshToken.create(
                user.userId(),
                refreshToken.token(),
                refreshToken.expiredAt()
        );

        refreshTokenRepository.save(token);

        // 6. 반환
        return new LoginResult(
                user.userId(),
                accessToken.token(),
                refreshToken.token()
        );
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
