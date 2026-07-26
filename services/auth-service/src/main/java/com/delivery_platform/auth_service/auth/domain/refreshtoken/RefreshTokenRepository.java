package com.delivery_platform.auth_service.auth.domain.refreshtoken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(UUID userId);

    void delete(RefreshToken refreshToken);

    void deleteByUserId(UUID userId);
}
