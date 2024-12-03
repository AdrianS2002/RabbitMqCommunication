package com.example.UsersMicroServices.dto;

import com.example.UsersMicroServices.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Builder
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String telephone;
    private Role role;
}
