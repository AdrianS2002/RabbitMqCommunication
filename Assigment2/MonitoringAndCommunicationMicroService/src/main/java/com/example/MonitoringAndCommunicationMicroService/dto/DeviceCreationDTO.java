package com.example.MonitoringAndCommunicationMicroService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
public class DeviceCreationDTO {

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("max_consumption")
    private double maxConsumption;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("device_id")
    private Long deviceId;

    public DeviceCreationDTO(@JsonProperty("operation") String operation,
                             @JsonProperty("max_consumption") double maxConsumption,
                             @JsonProperty("user_id") Long userId,
                             @JsonProperty("device_id") Long deviceId) {
        this.operation = operation;
        this.maxConsumption = maxConsumption;
        this.userId = userId;
        this.deviceId = deviceId;
    }
}
