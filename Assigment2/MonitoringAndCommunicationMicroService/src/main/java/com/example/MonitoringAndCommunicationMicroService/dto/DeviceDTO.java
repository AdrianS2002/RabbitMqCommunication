package com.example.MonitoringAndCommunicationMicroService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeviceDTO {

    @JsonProperty("device_id")
    private Long deviceId;

    @JsonProperty("max_consumption")
    private double maxConsumption;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("measurements")
    private List<MeasurementsDTO> measurements; // Reference DTO, not entity

    public DeviceDTO(@JsonProperty("device_id") Long deviceId,
                     @JsonProperty("max_consumption") double maxConsumption,
                     @JsonProperty("user_id") Long userId,
                     @JsonProperty("measurements") List<MeasurementsDTO> measurements) {
        this.deviceId = deviceId;
        this.maxConsumption = maxConsumption;
        this.userId = userId;
        this.measurements = measurements;
    }
}

