package com.demo.base.controller;

import com.demo.base.domain.User;
import com.demo.base.domain.response.Result;
import com.demo.base.repository.UserRepository;
import com.demo.base.service.AuthService;
import com.demo.base.util.annotation.*;
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

    @GetMapping("/profile")
    @RestrictAccess
    public Result<User> userProfile(@Inject(finder = UserRepository.class) User user) {
        return Result.success(user);
    }

    @GetMapping("/me/profile")
    @RestrictAccess
    public Result<User> myProfile(@InjectViaSession(key = "user") User user) {
        return Result.success(user);
    }

    @GetMapping("/logout")
    @RestrictAccess
    public Result<Void> logout() {
        return authService.logout();
    }
}