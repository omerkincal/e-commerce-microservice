package com.example.basket.library.feignclient;

import com.example.basket.domain.basket.api.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface AuthCallableFeignClient {
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable(name = "id") String userId);
}
