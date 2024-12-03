package com.example.UsersMicroServices.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity

@Table(name = "users")
@Data
@Setter
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name", nullable = false)
    private String username;



    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(Long userId, String username, String email, String password, Role role, String telephone) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.telephone = telephone;
    }

    public User() {

    }
}
