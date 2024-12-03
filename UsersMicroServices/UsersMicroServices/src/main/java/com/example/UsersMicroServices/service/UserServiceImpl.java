package com.example.UsersMicroServices.service;


import com.example.UsersMicroServices.dto.*;
import com.example.UsersMicroServices.exceptions.ApiExceptionResponse;
import com.example.UsersMicroServices.mapper.UserMapper;
import com.example.UsersMicroServices.mapper.UserToLoginMapper;
import com.example.UsersMicroServices.mapper.UserToLogoutMapper;
import com.example.UsersMicroServices.model.Role;
import com.example.UsersMicroServices.model.User;
import com.example.UsersMicroServices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{
   @Autowired
    private final UserRepository userRepository;

   @Value("${device.user.app}")
   private String DEVICE_USER_APP;
   //resttamplate

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private User sendPostRequest(UserCreationDTO userCreationDTO)
    {
        Map<String,String> variables = new HashMap<>();
        variables.put("username",userCreationDTO.getUsername());
        variables.put("email",userCreationDTO.getEmail());
        variables.put("telephone",userCreationDTO.getTelephone());

        ResponseEntity<UserDTO> responseEntity = new RestTemplate().postForEntity(DEVICE_USER_APP+"/users",variables,UserDTO.class);
        if(!responseEntity.getStatusCode().is2xxSuccessful())
            throw new ApiExceptionResponse("Error in creating user", HttpStatus.INTERNAL_SERVER_ERROR, null);
        return UserMapper.toEntity(Objects.requireNonNull(responseEntity.getBody()));
    }

    private void sendPutRequest(UserUpdateDTO userUpdateDTO)
    {
        Map<String,Object> variables = new HashMap<>();

        variables.put("userId",userUpdateDTO.getUserDTO().getUserId());

        if(userUpdateDTO.getUsername() != null)
            variables.put("username",userUpdateDTO.getUsername());
        else
            variables.put("username",userUpdateDTO.getUserDTO().getUsername());
        if(userUpdateDTO.getEmail() != null)
            variables.put("email",userUpdateDTO.getEmail());
        else
            variables.put("email",userUpdateDTO.getUserDTO().getEmail());
        if(userUpdateDTO.getTelephone() != null)
            variables.put("telephone",userUpdateDTO.getTelephone());
        else
            variables.put("telephone",userUpdateDTO.getUserDTO().getTelephone());

        ResponseEntity<UserDTO> responseEntity = new RestTemplate().exchange(DEVICE_USER_APP+"/users", HttpMethod.PUT, new HttpEntity<>(variables),UserDTO.class);
        if(!responseEntity.getStatusCode().is2xxSuccessful())
            throw new ApiExceptionResponse("Error in updating user", HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    private boolean sendDeleteRequest(Long userId)
    {
        ResponseEntity<Void> responseEntity = new RestTemplate().exchange(DEVICE_USER_APP+"/users/"+userId, HttpMethod.DELETE, null,Void.class);
        return responseEntity.getStatusCode().is2xxSuccessful();
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserDTO createUser(UserCreationDTO userCreationDTO) {

       User entity = sendPostRequest(userCreationDTO);
        entity.setRole(userCreationDTO.getRole());
        entity.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));
        if(entity.getRole() == null) {
            entity.setRole(Role.USER);
        }
        entity = userRepository.save(entity);
        return UserMapper.toDTO(entity);
    }


    @Override
    public UserDTO updateUser(UserDTO userDTO) {
         if(userDTO.getUserId() == null) {
             throw new RuntimeException("User ID is missing");
         }
         User entity = userRepository.findById(userDTO.getUserId()).orElseThrow(()->new RuntimeException("User not found"));
            entity.setUsername(userDTO.getUsername());
            entity.setEmail(userDTO.getEmail());
            entity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            entity.setTelephone(userDTO.getTelephone());
            userRepository.save(entity);
            return UserMapper.toDTO(entity);
    }



    @Override
    public String deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ApiExceptionResponse("User not found", HttpStatus.NOT_FOUND, null);
        }

        if(!sendDeleteRequest(userId))
        {
            throw new ApiExceptionResponse("Error in deleting user", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        userRepository.deleteById(userId);
        return "User deleted successfully";

    }

    @Override
    public UserDTO assignRole(Long userId, String role) {
        User entity = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        try {
            entity.setRole(Role.valueOf(role));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Role not found");
        }
        userRepository.save(entity);
        return UserMapper.toDTO(entity);
    }

    @Override
    public UserDTO updateUserRole(Long userId, Role role) {
        boolean exists = userRepository.findById(userId).isPresent();
        if (exists) {
            User user = userRepository.findById(userId).get();
            user.setRole(role);
            userRepository.save(user);
            return UserMapper.toDTO(user);
        }
        return null;
    }

    @Override
    public UserDTO updateAllUser(Long userId, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        sendPutRequest(userUpdateDTO);

        // Actualizează valorile din userDTO dacă sunt prezente
        if (userUpdateDTO.getUserDTO() != null) {
            if (userUpdateDTO.getUserDTO().getUsername() != null) {
                user.setUsername(userUpdateDTO.getUserDTO().getUsername());
            }
            if (userUpdateDTO.getUserDTO().getEmail() != null) {
                user.setEmail(userUpdateDTO.getUserDTO().getEmail());
            }
            if (userUpdateDTO.getUserDTO().getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userUpdateDTO.getUserDTO().getPassword()));
            }
            if (userUpdateDTO.getUserDTO().getTelephone() != null) {
                user.setTelephone(userUpdateDTO.getUserDTO().getTelephone());
            }
        }

        // Actualizează câmpurile din UserUpdateDTO (care sunt separate de userDTO)
        if (userUpdateDTO.getEmail() != null) {
            user.setEmail(userUpdateDTO.getEmail());
        }
        if (userUpdateDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        }
        if (userUpdateDTO.getTelephone() != null) {
            user.setTelephone(userUpdateDTO.getTelephone());
        }
        if (userUpdateDTO.getUsername() != null) {
            user.setUsername(userUpdateDTO.getUsername());
        }
        if (userUpdateDTO.getRole() != null) {
            user.setRole(userUpdateDTO.getRole());
        }

        // Salvează utilizatorul
        userRepository.save(user);

        // Returnează utilizatorul actualizat
        return UserMapper.toDTO(user);
    }



    @Override
    public SuccessfulLoginDTO login(AuthDTO auth) throws ApiExceptionResponse {
        User user = userRepository.findUserByUsername(auth.getUsername());
        if (user == null ||   !passwordEncoder.matches(auth.getPassword(), user.getPassword())){
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Invalid username or password.");

            throw ApiExceptionResponse.builder()
                    .errors(errors)
                    .message("Authentication failed")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        // Mapping user to SuccessfulLoginDTO
        return UserToLoginMapper.mapUserToDTO(user);
    }

    @Override
    public SuccessfulLogoutDTO logout(LogoutDTO dto) throws ApiExceptionResponse {
        User user = userRepository.findUserByUsername(dto.getUsername());
        if (user == null) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Invalid username or password.");

            throw ApiExceptionResponse.builder()
                    .errors(errors)
                    .message("Authentication failed")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        userRepository.save(user);
        return UserToLogoutMapper.mapUserToDTO(user);
    }

}
