package com.delivery_platform.test_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/api/test/health")
    public String test(){

        return "Test Service Response";

    }
}
