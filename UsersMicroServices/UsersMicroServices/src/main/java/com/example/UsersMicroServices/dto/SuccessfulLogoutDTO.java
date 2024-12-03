package com.example.UsersMicroServices.dto;

import com.example.UsersMicroServices.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessfulLogoutDTO {
    private Long id;
    private String username;
    private Role role;
}