package com.example.DevicesMicroservices.service;

import com.example.DevicesMicroservices.dto.DeviceCreationDto;
import com.example.DevicesMicroservices.dto.DeviceDto;
import com.example.DevicesMicroservices.model.Device;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceService {
    public List<Device> getAllDevices();
    public DeviceDto getDeviceById(Long deviceId);
    public DeviceDto addDevice(DeviceCreationDto deviceDto);
    public DeviceDto updateDevice(DeviceDto deviceDto);
    public void deleteDevice(Long deviceId);

    public List<DeviceDto> getAllDevicesByUser(Long userId);

}
