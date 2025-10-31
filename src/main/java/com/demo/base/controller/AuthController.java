package com.demo.base.controller;

import com.demo.base.domain.User;
import com.demo.base.domain.response.Result;
import com.demo.base.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public Result<Void> authenticate(@RequestBody @Valid User user) {
        return authService.login(user);
    }

    @GetMapping("/logout")
    public Result<Void> logout() {
        return authService.logout();
    }

    @GetMapping("/status")
    public Result<Void> status() {
        return Result.success(Thread.currentThread().getName());
    }
}