package com.delivery_platform.user_service.user.infrastructure.persistence;

import com.delivery_platform.user_service.user.domain.User;
import com.delivery_platform.user_service.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final SpringDataUserRepository repository;

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
