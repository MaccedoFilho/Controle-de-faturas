package com.macedo.controledefaturas.service;

import com.macedo.controledefaturas.dto.LoginRequest;
import com.macedo.controledefaturas.dto.LoginResponse;
import com.macedo.controledefaturas.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.macedo.controledefaturas.repository.UserRepository;
import com.macedo.controledefaturas.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = new JwtUtil();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("password does not match");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user.getId());
        loginResponse.setEmail(user.getEmail());

        String token = jwtUtil.generateToken(loginRequest);
        loginResponse.setToken(token);

        return loginResponse;
    }

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }
}
