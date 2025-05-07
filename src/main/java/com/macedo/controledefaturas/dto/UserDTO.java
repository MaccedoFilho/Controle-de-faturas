package com.macedo.controledefaturas.dto;

import com.macedo.controledefaturas.model.User;

public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String password;

    public UserDTO() {}
    public UserDTO(Long id, String email, String name, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public User toEntity() {
        User u = new User();
        u.setId(this.id);
        u.setName(this.name);
        u.setEmail(this.email);
        u.setPassword(this.password);
        return u;
    }

    public static UserDTO fromEntity(User u){
        return new UserDTO(
                u.getId(),
                u.getName(),
                u.getEmail(),
                null
        );
    }
}
