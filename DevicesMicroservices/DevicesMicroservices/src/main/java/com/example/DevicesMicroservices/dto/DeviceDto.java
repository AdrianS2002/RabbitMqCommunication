package com.example.DevicesMicroservices.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Builder
@Setter
@Getter
public class DeviceDto {
    private Long deviceId;
    private String description;
    private String address;
    private double consumption;
    private Long userId;

    @Override
    public String toString() {
        return "DeviceDto{" +
                "deviceId=" + deviceId +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", consumption=" + consumption +
                ", userId=" + userId +
                '}';
    }
}

