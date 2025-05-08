package com.macedo.controledefaturas.service;

import com.macedo.controledefaturas.exception.ResourceNotFoundException;
import com.macedo.controledefaturas.model.User;
import com.macedo.controledefaturas.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));
    }

    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public User updateUser(Long id, User dados) {
        User u = getUserById(id);
        u.setName(dados.getName());
        u.setEmail(dados.getEmail());
        u.setPassword(encoder.encode(dados.getPassword()));
        return repo.save(u);
    }

    public void deleteUser(Long id) {
        User u = getUserById(id);
        repo.delete(u);
    }
}
