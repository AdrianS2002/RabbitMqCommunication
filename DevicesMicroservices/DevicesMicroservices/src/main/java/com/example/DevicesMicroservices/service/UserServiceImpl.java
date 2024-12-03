package com.example.DevicesMicroservices.service;

import com.example.DevicesMicroservices.dto.UserCreationDto;
import com.example.DevicesMicroservices.dto.UserDto;
import com.example.DevicesMicroservices.mapper.UserMapper;
import com.example.DevicesMicroservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DevicesMicroservices.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDTO)  // Map each User entity to UserDto
                .collect(Collectors.toList());  // Return the list of UserDto
    }

    @Override
    public UserDto addUser(UserCreationDto userCreationDto) {
        User user = UserMapper.toCreationEntity(userCreationDto);  // Convert UserCreationDto to User entity
        User savedUser = userRepository.save(user);  // Save the user entity to the database
        return UserMapper.toDTO(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the entity with new data from the UserDto
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setTelephone(userDto.getTelephone());

        User updatedUser = userRepository.save(existingUser);  // Save the updated entity
        return UserMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(userId);
    }
}
