package com.example.UsersMicroServices.service;

import com.example.UsersMicroServices.dto.*;
import com.example.UsersMicroServices.exceptions.ApiExceptionResponse;
import com.example.UsersMicroServices.model.Role;
import com.example.UsersMicroServices.model.User;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User findByUsername(String username);
    User findByEmail(String email);
    UserDTO createUser(UserCreationDTO userCreationDTO);
    UserDTO updateUser(UserDTO userDTO);
    String deleteUser(Long userId);

    UserDTO assignRole(Long userId, String role);

    UserDTO updateUserRole(Long userId, Role role);
    UserDTO updateAllUser(Long userId, UserUpdateDTO userUpdateDTO);

    SuccessfulLoginDTO login(AuthDTO auth)throws ApiExceptionResponse;
    SuccessfulLogoutDTO logout (LogoutDTO dto) throws ApiExceptionResponse;


}
