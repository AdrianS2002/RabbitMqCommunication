package com.example.MonitoringAndCommunicationMicroService.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Long deviceId;
    private double measurementValue;
    private Long timestamp;
}