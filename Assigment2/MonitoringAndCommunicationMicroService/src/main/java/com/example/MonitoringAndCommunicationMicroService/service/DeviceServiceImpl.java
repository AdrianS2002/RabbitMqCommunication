package com.example.MonitoringAndCommunicationMicroService.service;

import com.example.MonitoringAndCommunicationMicroService.dto.DeviceCreationDTO;
import com.example.MonitoringAndCommunicationMicroService.dto.DeviceDTO;
import com.example.MonitoringAndCommunicationMicroService.mapper.DeviceMapper;
import com.example.MonitoringAndCommunicationMicroService.model.Device;
import com.example.MonitoringAndCommunicationMicroService.repository.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private final DeviceRepository deviceRepository;



    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    @Override
    public DeviceDTO findByDeviceId(Long deviceId) {
        Device device = deviceRepository.findByDeviceId(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));
        return DeviceMapper.toDto(device);
    }

    @Override
    public DeviceDTO save(DeviceCreationDTO deviceCreationDTO) {

        Device entity = DeviceMapper.toEntity(deviceCreationDTO);
        return DeviceMapper.toDto(deviceRepository.save(entity));
    }

    @Override
    public DeviceDTO updateDevice(DeviceDTO deviceDTO) {
        Device existingDevice = deviceRepository.findByDeviceId(deviceDTO.getDeviceId())
                .orElseThrow(() -> new RuntimeException("Device not found"));


        existingDevice.setHourConsumption(deviceDTO.getMaxConsumption());

        Device updatedDevice = deviceRepository.save(existingDevice);
        return DeviceMapper.toDto(updatedDevice);
    }

    @Override
    public void deleteDevice(Long deviceId) {
        Device device = deviceRepository.findByDeviceId(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));
        deviceRepository.delete(device);

    }
}
