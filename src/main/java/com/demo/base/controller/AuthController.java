package com.demo.base.controller;

import com.demo.base.domain.User;
import com.demo.base.domain.response.Result;
import com.demo.base.repository.UserRepository;
import com.demo.base.service.AuthService;
import com.demo.base.util.annotation.LogLevel;
import com.demo.base.util.annotation.Monitor;
import com.demo.base.util.annotation.ResourceInjector;
import com.demo.base.util.annotation.RestrictAccess;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    @Monitor(level = LogLevel.INFO)
    public Result<Void> authenticate(@RequestBody @Valid User user) {
        return authService.login(user);
    }

    @GetMapping("/profile")
    @RestrictAccess
    public Result<User> userProfile(@ResourceInjector(finder = UserRepository.class,target = "user") BigInteger id, User user) {
        return Result.success(user);
    }

    @GetMapping("/logout")
    @RestrictAccess
    public Result<Void> logout() {
        return authService.logout();
    }
}