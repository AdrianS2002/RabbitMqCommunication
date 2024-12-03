package com.example.MonitoringAndCommunicationMicroService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "devices")
@NoArgsConstructor
public class Device {

    @Id
    @Column(name = "id_device")
    private Long deviceId;

    @Column(name = "max_consumption")
    private double hourConsumption;

    @Column(name = "user_id")
    private Long userId;

    @JsonIgnore
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Measurements> measurements;

    public Device(Long deviceId, double hourConsumption, Long userId) {
        this.deviceId = deviceId;
        this.hourConsumption = hourConsumption;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId=" + deviceId +
                ", hourConsumption=" + hourConsumption +
                ", userId=" + userId +
                '}';
    }
}
