package com.example.DevicesMicroservices.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceChangeEvent implements java.io.Serializable{
    private Long deviceId;
    private Long userId;
    private double hourConsumption;
    private String operation; // "create", "update", "delete"
    private Long timestamp;

    public DeviceChangeEvent(Long deviceId, String operation, Long timestamp) {
        this.deviceId = deviceId;
        this.operation = operation;
        this.timestamp = timestamp;
    }
}