package com.delivery_platform.auth_service.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.delivery_platform.auth_service")
public class OpenFeignConfig {

}
