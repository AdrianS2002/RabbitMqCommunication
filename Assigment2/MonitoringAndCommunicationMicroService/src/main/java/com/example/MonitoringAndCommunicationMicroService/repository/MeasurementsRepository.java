package com.example.MonitoringAndCommunicationMicroService.repository;

import com.example.MonitoringAndCommunicationMicroService.dto.MeasurementsCreationDTO;
import com.example.MonitoringAndCommunicationMicroService.dto.MeasurementsDTO;
import com.example.MonitoringAndCommunicationMicroService.model.Device;
import com.example.MonitoringAndCommunicationMicroService.model.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.example.MonitoringAndCommunicationMicroService.model.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Long> {

    Optional<Measurements> findByMeasurementId(Long measurementId);

    List<Measurements> findByDeviceAndTimestampBetween(Device device, Long startTimestamp, Long endTimestamp);
    @Query("SELECT m FROM Measurements m WHERE m.device.deviceId = :deviceId")
    List<Measurements> findByDeviceId(@Param("deviceId") Long deviceId);

}
