package com.delivery_platform.user_service.user.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

    Optional<User> findByUsername(String username);

}
