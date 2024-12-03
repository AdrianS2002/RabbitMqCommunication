package com.example.DevicesMicroservices.controller;

import com.example.DevicesMicroservices.dto.UserCreationDto;
import com.example.DevicesMicroservices.dto.UserDto;
import com.example.DevicesMicroservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

@CrossOrigin
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreationDto userCreationDto) {
        return ResponseEntity.ok(userService.addUser(userCreationDto));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDto userCreationDto) {
        return ResponseEntity.ok(userService.updateUser(userCreationDto));
    }

}
