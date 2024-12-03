package com.example.MonitoringAndCommunicationMicroService.mapper;

import com.example.MonitoringAndCommunicationMicroService.dto.DeviceCreationDTO;
import com.example.MonitoringAndCommunicationMicroService.dto.DeviceDTO;
import com.example.MonitoringAndCommunicationMicroService.dto.MeasurementsDTO;
import com.example.MonitoringAndCommunicationMicroService.model.Device;
import com.example.MonitoringAndCommunicationMicroService.model.Measurements;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceMapper {

    // Convert Device entity to DeviceDTO
    public static DeviceDTO toDto(Device device) {
        return DeviceDTO.builder()
                .deviceId(device.getDeviceId())
                .maxConsumption(device.getHourConsumption())
                .userId(device.getUserId())
                .measurements(device.getMeasurements() != null ? toMeasurementsDtoList(device.getMeasurements()) : null)
                .build();
    }

    // Convert DeviceDTO to Device entity
    public static Device toEntity(DeviceDTO deviceDto) {
        Device device = new Device();
        device.setDeviceId(deviceDto.getDeviceId());
        device.setHourConsumption(deviceDto.getMaxConsumption());
        device.setUserId(deviceDto.getUserId());
        device.setMeasurements(deviceDto.getMeasurements() != null ? toMeasurementsEntityList(deviceDto.getMeasurements(), device) : null);
        return device;
    }

    // Convert DeviceCreationDTO to Device entity
    public static Device toEntity(DeviceCreationDTO creationDto) {
        Device device = new Device();
        device.setDeviceId(creationDto.getDeviceId());
        device.setHourConsumption(creationDto.getMaxConsumption());
        device.setUserId(creationDto.getUserId());
        // DeviceCreationDTO typically doesn’t include measurements
        return device;
    }

    // Convert Device entity to DeviceCreationDTO
    public static DeviceCreationDTO toCreationDto(Device device, String operation) {
        return DeviceCreationDTO.builder()
                .operation(operation)
                .deviceId(device.getDeviceId())
                .maxConsumption(device.getHourConsumption())
                .userId(device.getUserId())
                .build();
    }

    // Convert List of Measurements entity to MeasurementsDTO
    private static List<MeasurementsDTO> toMeasurementsDtoList(List<Measurements> measurements) {
        return measurements.stream()
                .map(MeasurementsMapper::toDto)
                .collect(Collectors.toList());
    }

    // Convert List of MeasurementsDTO to Measurements entity
    private static List<Measurements> toMeasurementsEntityList(List<MeasurementsDTO> measurementsDto, Device device) {
        return measurementsDto.stream()
                .map(dto -> MeasurementsMapper.toEntity(dto, device))
                .collect(Collectors.toList());
    }
}
