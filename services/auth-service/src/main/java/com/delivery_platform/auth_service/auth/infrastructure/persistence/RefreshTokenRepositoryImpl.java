package com.delivery_platform.auth_service.auth.infrastructure.persistence;

import com.delivery_platform.auth_service.auth.domain.refreshtoken.RefreshToken;
import com.delivery_platform.auth_service.auth.domain.refreshtoken.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final SpringDataRefreshTokenRepository springDataRefreshTokenRepository;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return springDataRefreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return springDataRefreshTokenRepository.findByToken(token);
    }

    @Override
    public Optional<RefreshToken> findByUserId(UUID userId) {
        return springDataRefreshTokenRepository.findByUserId(userId);
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        springDataRefreshTokenRepository.delete(refreshToken);
    }

    @Override
    public void deleteByUserId(UUID userId) {
        springDataRefreshTokenRepository.deleteByUserId(userId);
    }
}
