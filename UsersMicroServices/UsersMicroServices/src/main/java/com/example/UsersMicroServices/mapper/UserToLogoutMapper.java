package com.example.UsersMicroServices.mapper;

import com.example.UsersMicroServices.dto.SuccessfulLogoutDTO;
import com.example.UsersMicroServices.model.User;

public class UserToLogoutMapper {
    public static SuccessfulLogoutDTO mapUserToDTO(User user) {
        if (user == null) {
            return null;
        }
        return SuccessfulLogoutDTO.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
