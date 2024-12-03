package com.example.UsersMicroServices;

import com.example.UsersMicroServices.dto.UserCreationDTO;
import com.example.UsersMicroServices.mapper.UserMapper;
import com.example.UsersMicroServices.model.Role;
import com.example.UsersMicroServices.model.User;
import com.example.UsersMicroServices.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;

@SpringBootApplication
public class UsersMicroServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersMicroServicesApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(UserService userService) {
		return (args) -> {
			User user1 = new User();
			user1.setUsername("user1");
			user1.setEmail("aschiop2002@yahoo.com");
			user1.setPassword("Password1");
			UserCreationDTO userCreationDTO = UserMapper.toCreateDTO(user1);
			userService.createUser(userCreationDTO);

			User user2 = new User();
			user2.setUsername("user2");
			user2.setEmail("adsdsa@yahoo.com");
			user2.setPassword("Password2");
			userCreationDTO = UserMapper.toCreateDTO(user2);
			userService.createUser(userCreationDTO);

			System.out.println(userService.getAllUsers());
			System.out.println("**************************************************************");

			// Aici extragi utilizatorul din baza de date ca să aibă un ID setat
			User existingUser = userService.getUserById(1L); // Extragem utilizatorul pentru a avea ID
			existingUser.setEmail("baba@gmail.com");
			existingUser.setPassword("AnaAremer22");
			existingUser.setUsername("Ana");

			userService.updateUser(UserMapper.toDTO(existingUser));

			System.out.println(userService.getAllUsers());
			System.out.println("**************************************************************");

			userService.deleteUser(2L);
			System.out.println(userService.getAllUsers());

			userService.assignRole(1L, "ADMIN");
			System.out.println(userService.getAllUsers());

			User user3 = new User();
			user3.setUsername("user3");
			user3.setEmail("azzza@yahoo.com");
			user3.setPassword("Password2");
			userCreationDTO = UserMapper.toCreateDTO(user3);
			userService.createUser(userCreationDTO);

			User user4 = new User();
			user4.setUsername("user4");
			user4.setEmail("nlnjgd@yahoo.com");
			user4.setPassword("Password2");
			userCreationDTO = UserMapper.toCreateDTO(user4);
			userService.createUser(userCreationDTO);

			System.out.println(userService.findByUsername("user3"));
			System.out.println("**************************************************************");

			User existingUser1 = userService.getUserById(4L); // Extragem utilizatorul pentru a avea ID
			existingUser1.setEmail("aschiop2002@yahoo.com");
			userService.updateUser(UserMapper.toDTO(existingUser1));
			System.out.println(userService.findByEmail("aschiop2002@yahoo.com"));


		};
	}*/
}
