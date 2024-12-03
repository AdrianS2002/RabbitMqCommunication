package com.example.MonitoringAndCommunicationMicroService.service;

import com.example.MonitoringAndCommunicationMicroService.dto.MeasurementsCreationDTO;
import com.example.MonitoringAndCommunicationMicroService.dto.MeasurementsDTO;
import com.example.MonitoringAndCommunicationMicroService.mapper.MeasurementsMapper;
import com.example.MonitoringAndCommunicationMicroService.model.Device;
import com.example.MonitoringAndCommunicationMicroService.model.Measurements;
import com.example.MonitoringAndCommunicationMicroService.repository.DeviceRepository;
import com.example.MonitoringAndCommunicationMicroService.repository.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class MeasurementsServiceImpl implements MeasurementsService{

    @Autowired
    private final MeasurementsRepository measurementsRepository;

    private final DeviceRepository deviceRepository;

    public MeasurementsServiceImpl(MeasurementsRepository measurementsRepository, DeviceRepository deviceRepository) {
        this.measurementsRepository = measurementsRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Measurements findByMeasurementId(Long measurementId) {
        return measurementsRepository.findByMeasurementId(measurementId)
                .orElseThrow(() -> new RuntimeException("Measurement not found"));
    }


    @Override
    public void saveMeasurement(Measurements measurement) {
        measurementsRepository.save(measurement);
    }

    @Override
    public MeasurementsDTO save(MeasurementsCreationDTO measurementsCreationDTO) {
        // Fetch the associated Device
        Device device = deviceRepository.findByDeviceId(measurementsCreationDTO.getDeviceId())
                .orElseThrow(() -> new RuntimeException("Device not found"));

        // Convert DTO to entity and set the device
        Measurements measurements = MeasurementsMapper.toEntity(measurementsCreationDTO, device);

        // Save and return the saved entity as DTO
        return MeasurementsMapper.toDto(measurementsRepository.save(measurements));
    }



    @Override
    public MeasurementsDTO updateMeasurements(MeasurementsDTO measurementsDTO) {
        Measurements existingMeasurement = measurementsRepository.findByMeasurementId(measurementsDTO.getMeasurementId())
                .orElseThrow(() -> new RuntimeException("Measurement not found"));

        existingMeasurement.setConsumption(measurementsDTO.getConsumption());
        existingMeasurement.setTimestamp(Long.valueOf(measurementsDTO.getTimestamp()));


        Measurements updatedMeasurement = measurementsRepository.save(existingMeasurement);
        return MeasurementsMapper.toDto(updatedMeasurement);
    }

    @Override
    public void deleteMeasurements(Long measurementId) {
        Measurements measurements = measurementsRepository.findByMeasurementId(measurementId)
                .orElseThrow(() -> new RuntimeException("Measurement not found"));
        measurementsRepository.delete(measurements);

    }

    @Override
    public List<Measurements> getMeasurementsByDeviceAndDate(Long deviceId, LocalDate date) {
        // Fetch the device entity from the repository
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        // Define the start and end of the day
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        // Convert LocalDateTime to timestamp for querying
        long startTimestamp = startOfDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endTimestamp = endOfDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // Query the measurements repository for the device and date range
        return measurementsRepository.findByDeviceAndTimestampBetween(device, startTimestamp, endTimestamp);
    }
    @Override
    public List<Measurements> getMeasurementsByDevice(Long deviceId) {
        return measurementsRepository.findByDeviceId(deviceId);
    }

}
