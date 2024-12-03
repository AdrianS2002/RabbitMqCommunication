package com.example.UsersMicroServices.controller;

import com.example.UsersMicroServices.dto.AuthDTO;
import com.example.UsersMicroServices.dto.LogoutDTO;
import com.example.UsersMicroServices.exceptions.ApiExceptionResponse;
import com.example.UsersMicroServices.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO auth) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(auth));
    }
    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody LogoutDTO logoutDTO) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.logout(logoutDTO));
    }


}
