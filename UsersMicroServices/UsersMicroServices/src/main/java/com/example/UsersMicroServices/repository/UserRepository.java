package com.example.UsersMicroServices.repository;

import com.example.UsersMicroServices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public void deleteUserByUsername(String username);
    public User findUserByUsername(String username);
    public User findUserByEmail(String email);
}
