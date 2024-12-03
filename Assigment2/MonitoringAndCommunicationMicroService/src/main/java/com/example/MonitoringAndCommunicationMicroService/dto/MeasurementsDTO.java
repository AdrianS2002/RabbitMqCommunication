package com.example.MonitoringAndCommunicationMicroService.dto;

import com.example.MonitoringAndCommunicationMicroService.model.Device;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class MeasurementsDTO {

    @JsonProperty("measurement_id")
    private Long measurementId;

    @JsonProperty("device_id") // Fixed casing for JSON serialization
    private Long deviceId;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("consumption")
    private double consumption;

    public MeasurementsDTO(@JsonProperty("measurement_id") Long measurementId,
                           @JsonProperty("device_id") Long deviceId,
                           @JsonProperty("timestamp") String timestamp,
                           @JsonProperty("consumption") double consumption) {
        this.measurementId = measurementId;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.consumption = consumption;
    }
}
