package com.example.UsersMicroServices.security;


import com.example.UsersMicroServices.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final com.example.UsersMicroServices.model.User userAuthFromDB = userRepository.findUserByUsername(username);
        if (userAuthFromDB == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.withUsername(userAuthFromDB.getUsername())
                .password(userAuthFromDB.getPassword())
                .roles(userAuthFromDB.getRole().name())  // Asigură-te că returnezi rolul corect
                .build();
    }

}