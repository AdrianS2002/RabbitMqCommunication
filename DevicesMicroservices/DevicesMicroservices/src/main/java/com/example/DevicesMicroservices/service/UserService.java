package com.example.DevicesMicroservices.service;

import com.example.DevicesMicroservices.model.User;
import com.example.DevicesMicroservices.dto.UserCreationDto;
import com.example.DevicesMicroservices.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto addUser(UserCreationDto userCreationDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long userId);
}