package com.example.MonitoringAndCommunicationMicroService.repository;

import com.example.MonitoringAndCommunicationMicroService.dto.DeviceCreationDTO;
import com.example.MonitoringAndCommunicationMicroService.dto.DeviceDTO;
import com.example.MonitoringAndCommunicationMicroService.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    public Optional<Device> findByDeviceId(Long deviceId);



}
