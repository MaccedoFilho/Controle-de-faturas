package com.macedo.controledefaturas.controller;

import com.macedo.controledefaturas.dto.UserDTO;
import com.macedo.controledefaturas.model.User;
import com.macedo.controledefaturas.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listAll() {
        List<UserDTO> dtos = service.getAllUsers().stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        User u = service.getUserById(id);
        return ResponseEntity.ok(UserDTO.fromEntity(u));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(
            @Valid @RequestBody UserDTO dto
    ) {
        User saved = service.createUser(dto.toEntity());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO dto
    ) {
        User updated = service.updateUser(id, dto.toEntity());
        return ResponseEntity.ok(UserDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}