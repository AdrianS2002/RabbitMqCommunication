package com.example.UsersMicroServices.mapper;

import com.example.UsersMicroServices.dto.UserCreationDTO;
import com.example.UsersMicroServices.dto.UserDTO;
import com.example.UsersMicroServices.model.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .telephone(user.getTelephone())
                .role(user.getRole())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        return new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getRole(),
                userDTO.getTelephone()
        );
    }

    public static User toCreateEntity(UserCreationDTO userCreationDTO) {
        return new User(
                null,  // userId va fi generat automat la salvare
                userCreationDTO.getUsername(),
                userCreationDTO.getEmail(),
                userCreationDTO.getPassword(),
                null,  // Role-ul poate fi setat ulterior sau implicit în cod
                userCreationDTO.getTelephone()
        );
    }

    public static UserCreationDTO toCreateDTO(User user) {
        return UserCreationDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .telephone(user.getTelephone())
                .build();
    }


}
