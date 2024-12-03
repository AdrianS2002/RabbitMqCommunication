package com.example.MonitoringAndCommunicationMicroService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "measurements")
@NoArgsConstructor
public class Measurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id")
    private Long measurementId;

    @Column(name = "timestamp")
    private Long timestamp;

    @Column(name = "consumption")
    private double consumption;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Transient
    @JsonProperty("deviceId")
    private Long deviceId;

    public Measurements(Long timestamp, double consumption, Device device) {
        this.timestamp = timestamp;
        this.consumption = consumption;
        this.device = device;
    }

    @Override
    public String toString() {
        return "Measurements{" +
                "measurementId=" + measurementId +
                ", timestamp=" + timestamp +
                ", consumption=" + consumption +
                '}';
    }
}
