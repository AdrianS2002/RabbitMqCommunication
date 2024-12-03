package com.example.MonitoringAndCommunicationMicroService.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceChangeEvent {
    private Long deviceId;
    private double hourConsumption;
    private Long userId;
    private String operation; // "create", "update", or "delete"
}
