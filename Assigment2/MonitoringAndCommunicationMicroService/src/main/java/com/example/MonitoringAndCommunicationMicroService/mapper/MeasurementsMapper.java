package com.example.MonitoringAndCommunicationMicroService.mapper;

import com.example.MonitoringAndCommunicationMicroService.dto.MeasurementsCreationDTO;
import com.example.MonitoringAndCommunicationMicroService.dto.MeasurementsDTO;
import com.example.MonitoringAndCommunicationMicroService.model.Device;
import com.example.MonitoringAndCommunicationMicroService.model.Measurements;

public class MeasurementsMapper {

    // Converts Measurements entity to MeasurementsDTO
    public static MeasurementsDTO toDto(Measurements measurements) {
        return MeasurementsDTO.builder()
                .measurementId(measurements.getMeasurementId())
                .deviceId(measurements.getDevice() != null ? measurements.getDevice().getDeviceId() : null) // Check for null
                .timestamp(String.valueOf(measurements.getTimestamp()))
                .consumption(measurements.getConsumption())
                .build();
    }

    // Converts MeasurementsDTO to Measurements entity
    public static Measurements toEntity(MeasurementsDTO measurementsDto, Device device) {
        Measurements measurements = new Measurements();
        measurements.setMeasurementId(measurementsDto.getMeasurementId());
        measurements.setTimestamp(Long.valueOf(measurementsDto.getTimestamp()));
        measurements.setConsumption(measurementsDto.getConsumption());
        measurements.setDevice(device); // Set the associated Device object
        return measurements;
    }

    // Converts MeasurementsCreationDTO to Measurements entity
    public static Measurements toEntity(MeasurementsCreationDTO measurementsCreationDTO, Device device) {
        Measurements measurements = new Measurements();
        measurements.setTimestamp(Long.valueOf(measurementsCreationDTO.getTimestamp()));
        measurements.setConsumption(measurementsCreationDTO.getConsumption());
        measurements.setDevice(device); // Set the associated Device object
        return measurements;
    }

    // Converts Measurements entity to MeasurementsCreationDTO
    public static MeasurementsCreationDTO toCreationDto(Measurements measurements) {
        return MeasurementsCreationDTO.builder()
                .deviceId(measurements.getDevice() != null ? measurements.getDevice().getDeviceId() : null) // Check for null
                .timestamp(String.valueOf(measurements.getTimestamp()))
                .consumption(measurements.getConsumption())
                .build();
    }
}
