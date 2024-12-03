package com.example.UsersMicroServices.dto;

import com.example.UsersMicroServices.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessfulLoginDTO {
    private Long id;
    private Role role;
    private String username;
    private String email;
    private String telephone;

}
