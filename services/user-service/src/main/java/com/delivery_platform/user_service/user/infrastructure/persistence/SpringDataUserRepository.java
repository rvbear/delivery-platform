package com.delivery_platform.user_service.user.infrastructure.persistence;

import com.delivery_platform.user_service.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
