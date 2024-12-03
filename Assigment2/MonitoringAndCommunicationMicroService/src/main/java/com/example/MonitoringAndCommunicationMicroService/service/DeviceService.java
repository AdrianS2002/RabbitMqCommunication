package com.example.MonitoringAndCommunicationMicroService.service;

import com.example.MonitoringAndCommunicationMicroService.dto.DeviceCreationDTO;
import com.example.MonitoringAndCommunicationMicroService.dto.DeviceDTO;
import org.springframework.stereotype.Component;

@Component
public interface DeviceService {
    public DeviceDTO findByDeviceId(Long deviceId);
    public DeviceDTO save(DeviceCreationDTO deviceCreationDTO);

    public DeviceDTO updateDevice(DeviceDTO deviceDTO);
    public void deleteDevice(Long deviceId);
}
