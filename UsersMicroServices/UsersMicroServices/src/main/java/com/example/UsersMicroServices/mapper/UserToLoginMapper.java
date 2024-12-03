package com.example.UsersMicroServices.mapper;

import com.example.UsersMicroServices.dto.SuccessfulLoginDTO;
import com.example.UsersMicroServices.model.User;

public class UserToLoginMapper {
    public static SuccessfulLoginDTO mapUserToDTO(User user) {
        if (user == null) {
            return null;
        }
        return SuccessfulLoginDTO.builder()
                .id(user.getUserId())
                .role(user.getRole())
                .username(user.getUsername())
                .email(user.getEmail())
                .telephone(user.getTelephone())
                .build();
    }
}
