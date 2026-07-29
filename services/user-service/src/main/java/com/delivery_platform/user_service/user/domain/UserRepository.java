package com.delivery_platform.user_service.user.domain;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

}
