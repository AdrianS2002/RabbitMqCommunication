package com.example.UsersMicroServices.dto;

import com.example.UsersMicroServices.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCreationDTO {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[a-zA-Z\\d\\W_]{8,}$", message = "Password must contain at least 8 characters, one uppercase letter, one lowercase letter and one number.")
    private String password;

    @NotEmpty(message = "Telephone cannot be empty")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Telephone number must contain 10 digits.")
    private String telephone;

    private Role role;
}
