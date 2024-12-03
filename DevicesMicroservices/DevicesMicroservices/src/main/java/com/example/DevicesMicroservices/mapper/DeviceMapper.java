package com.example.DevicesMicroservices.mapper;

import com.example.DevicesMicroservices.dto.DeviceCreationDto;
import com.example.DevicesMicroservices.dto.DeviceDto;
import com.example.DevicesMicroservices.model.Device;
import com.example.DevicesMicroservices.model.User;

public class DeviceMapper {

    public static DeviceDto toDeviceDto(Device device) {
        return DeviceDto.builder()
                .deviceId(device.getDeviceId())
                .description(device.getDescription())
                .address(device.getAddress())
                .consumption(device.getConsumption())
                .userId(device.getUser().getUserId())  // Make sure userId is set properly
                .build();
    }

    public static Device toEntity(DeviceDto deviceDto, User user) {
        Device device = new Device();
        device.setDeviceId(deviceDto.getDeviceId());    // Setează ID-ul device-ului
        device.setDescription(deviceDto.getDescription());  // Setează descrierea
        device.setAddress(deviceDto.getAddress());  // Setează adresa
        device.setConsumption(deviceDto.getConsumption());  // Setează consumul
        device.setUser(user);  // Asociază user-ul corespunzător
        return device;  // Returnează entitatea `Device`
    }


    public static Device toCreationEntity(DeviceCreationDto deviceCreationDto, User user) {
        Device device = new Device();
        device.setDescription(deviceCreationDto.getDescription());  // Setează descrierea
        device.setAddress(deviceCreationDto.getAddress());  // Setează adresa
        device.setConsumption(deviceCreationDto.getConsumption());  // Setează consumul
        device.setUser(user);  // Asociază user-ul corespunzător
        return device;  // Returnează entitatea `Device`
    }


    public static DeviceCreationDto toCreationDto(Device device) {
        return DeviceCreationDto.builder()
                .description(device.getDescription())
                .address(device.getAddress())
                .consumption(device.getConsumption())
                .userId(device.getUser().getUserId())  // Set userId from the User entity
                .build();
    }

}
