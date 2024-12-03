package com.example.DevicesMicroservices;

import com.example.DevicesMicroservices.dto.DeviceCreationDto;
import com.example.DevicesMicroservices.dto.DeviceDto;
import com.example.DevicesMicroservices.dto.UserCreationDto;
import com.example.DevicesMicroservices.dto.UserDto;
import com.example.DevicesMicroservices.mapper.DeviceMapper;
import com.example.DevicesMicroservices.mapper.UserMapper;
import com.example.DevicesMicroservices.model.Device;
import com.example.DevicesMicroservices.model.User;
import com.example.DevicesMicroservices.service.DeviceService;
import com.example.DevicesMicroservices.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DevicesMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevicesMicroservicesApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(UserService userService, DeviceService deviceService) {
		return (args) -> {
			User user1 = new User();
			user1.setUsername("user1");
			user1.setEmail("aschiop2002@yahoo.com");
			user1.setTelephone("0740123456");
			UserCreationDto userCreationDto = UserMapper.toCreationDto(user1);
			userService.addUser(userCreationDto);


			User user2 = new User();
			user2.setUsername("user2");
			user2.setEmail("adsdsa@yahoo.com");
			user2.setTelephone("0743123456");
			UserCreationDto userCreationDto2 = UserMapper.toCreationDto(user2);
			userService.addUser(userCreationDto2);

			System.out.println("Users found with findAll():" + userService.getAllUsers());
			System.out.println("**************************************************************************");

			System.out.println("User found with findById(1L):" + userService.getUserById(1L));


			UserDto existingUser = userService.getUserById(1L);
			existingUser.setUsername("user1_updated");
			userService.updateUser(existingUser);
			System.out.println("**************************************************************************");
			System.out.println("Users found with findAll():" + userService.getAllUsers());

			userService.deleteUser(2L);

			System.out.println("Users found with findAll():" + userService.getAllUsers());
			System.out.println("**************************************************************************");

			UserDto userDto = userService.getUserById(1L);

			Device device1 = new Device();
			device1.setDescription("device1");
			device1.setUser(UserMapper.toEntity(userDto));
			device1.setConsumption(100.0);
			device1.setAddress("address1");

			DeviceCreationDto deviceCreationDto = DeviceMapper.toCreationDto(device1);
			deviceService.addDevice(deviceCreationDto);

			Device device2 = new Device();
			device2.setDescription("device2");
			device2.setUser(UserMapper.toEntity(userDto));
			device2.setConsumption(200.0);
			device2.setAddress("address2");

			DeviceCreationDto deviceCreationDto2 = DeviceMapper.toCreationDto(device2);
			deviceService.addDevice(deviceCreationDto2);

			System.out.println("Devices found with findAll():" + deviceService.getAllDevices());
			System.out.println("**************************************************************************");

			System.out.println("Device found with findById(1L):" + deviceService.getDeviceById(1L));
			System.out.println("**************************************************************************");

			System.out.println("Device from users found with findAll():" + deviceService.getAllDevicesByUser(1L));

			DeviceDto existingDevice = deviceService.getDeviceById(1L);
			existingDevice.setDescription("device1_updated");
			deviceService.updateDevice(existingDevice);
			System.out.println("**************************************************************************");
			System.out.println("Devices found with findAll():" + deviceService.getAllDevices());

		*//*	deviceService.deleteDevice(2L);
			System.out.println("**************************************************************************");
			System.out.println("Devices found with findAll():" + deviceService.getAllDevices());

			userService.deleteUser(1L);
			System.out.println("**************************************************************************");
			System.out.println("Users found with findAll():" + userService.getAllUsers());
			System.out.println("**************************************************************************");
			System.out.println("Devices found with findAll():" + deviceService.getAllDevices());*//*


		};
	}*/
}