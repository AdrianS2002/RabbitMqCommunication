package com.example.MonitoringAndCommunicationMicroService.service;

import com.example.MonitoringAndCommunicationMicroService.dto.DeviceDTO;
import com.example.MonitoringAndCommunicationMicroService.dto.MeasurementsCreationDTO;
import com.example.MonitoringAndCommunicationMicroService.dto.MeasurementsDTO;
import com.example.MonitoringAndCommunicationMicroService.model.Device;
import com.example.MonitoringAndCommunicationMicroService.model.Measurements;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface MeasurementsService {
    public Measurements findByMeasurementId(Long measurementId);
    public void saveMeasurement(Measurements measurement);
    public List<Measurements> getMeasurementsByDevice(Long deviceId);
    public MeasurementsDTO save(MeasurementsCreationDTO measurementsDTO);
    public MeasurementsDTO updateMeasurements(MeasurementsDTO measurementsDTO);
    public void deleteMeasurements(Long measurementId);
    List<Measurements> getMeasurementsByDeviceAndDate(Long deviceId, LocalDate date);

}
