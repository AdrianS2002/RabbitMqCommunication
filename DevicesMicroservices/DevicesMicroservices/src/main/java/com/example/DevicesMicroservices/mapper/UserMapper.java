package com.example.DevicesMicroservices.mapper;

import com.example.DevicesMicroservices.dto.UserCreationDto;
import com.example.DevicesMicroservices.dto.UserDto;
import com.example.DevicesMicroservices.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserMapper {
    public static UserDto toDTO(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .telephone(user.getTelephone())
                .build();
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());    // Setează ID-ul
        user.setUsername(userDto.getUsername());  // Setează username-ul
        user.setEmail(userDto.getEmail());    // Setează email-ul
        user.setTelephone(userDto.getTelephone());  // Setează numărul de telefon
        return user;  // Returnează entitatea `User`
    }


    public static User toCreationEntity(UserCreationDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());  // Setează username-ul
        user.setEmail(userDto.getEmail());    // Setează email-ul
        user.setTelephone(userDto.getTelephone());  // Setează numărul de telefon
        return user;  // Returnează entitatea `User`
    }
    public static UserCreationDto  toCreationDto(User user) {
        return UserCreationDto .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .telephone(user.getTelephone())
                .build();
    }
}
