package com.example.DevicesMicroservices.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephone")
    private String telephone;

    public User() {
    }

    public User(Long userId, String username, String email, String telephone) {
        this.username = username;
        this.email = email;
        this.telephone = telephone;
        this.userId = userId;
    }

}
