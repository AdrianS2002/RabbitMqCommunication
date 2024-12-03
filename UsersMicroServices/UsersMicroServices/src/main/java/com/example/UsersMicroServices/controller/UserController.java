package com.example.UsersMicroServices.controller;

import com.example.UsersMicroServices.dto.UserCreationDTO;
import com.example.UsersMicroServices.dto.UserDTO;
import com.example.UsersMicroServices.dto.UserUpdateDTO;
import com.example.UsersMicroServices.exceptions.ApiExceptionResponse;
import com.example.UsersMicroServices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

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

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreationDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping
    public ResponseEntity updateUser( @RequestBody UserUpdateDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateAllUser(userDTO.getUserDTO().getUserId(), userDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {

            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
