package com.macedo.controledefaturas.controller;

import com.macedo.controledefaturas.dto.LoginRequest;
import com.macedo.controledefaturas.dto.LoginResponse;
import com.macedo.controledefaturas.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macedo.controledefaturas.service.AuthService;

@RestController
@RequestMapping("/auth")

public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String message = authService.register(user);
        return ResponseEntity.ok(message);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse resp = authService.login(loginRequest);
        return ResponseEntity.ok(resp);
    }
}

