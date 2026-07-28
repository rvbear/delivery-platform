package com.delivery_platform.user_service.user.application;

import com.delivery_platform.user_service.global.exception.BusinessException;
import com.delivery_platform.user_service.global.exception.ErrorCode;
import com.delivery_platform.user_service.user.application.result.LoginUserResult;
import com.delivery_platform.user_service.user.domain.User;
import com.delivery_platform.user_service.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public LoginUserResult getLoginUser(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return new LoginUserResult(
                user.getId(),
                user.getPassword(),
                user.getRole().name()
        );
    }
}
