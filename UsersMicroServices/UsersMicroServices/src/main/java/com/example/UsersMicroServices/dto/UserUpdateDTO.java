package com.example.UsersMicroServices.dto;

import com.example.UsersMicroServices.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    private UserDTO userDTO;
    private Role role;
    //astea de jos sterse si apelat din nou metoda updateUserRoles din UserService cu ROles ca parametru
    private String telephone;
    private String password;
    private String email;
    private String username;
}
