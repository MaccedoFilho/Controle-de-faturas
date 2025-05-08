package com.macedo.controledefaturas.controller;

import com.macedo.controledefaturas.dto.LoginRequest;
import com.macedo.controledefaturas.dto.LoginResponse;
import com.macedo.controledefaturas.dto.UserDTO;
import com.macedo.controledefaturas.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<UserDTO> register(
            @Valid @RequestBody UserDTO dto
    ) {
        User user = authService.register(dto.toEntity());
        UserDTO out = UserDTO.fromEntity(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(out);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest req
    ) {
        LoginResponse resp = authService.login(req);
        return ResponseEntity.ok(resp);
    }
}

