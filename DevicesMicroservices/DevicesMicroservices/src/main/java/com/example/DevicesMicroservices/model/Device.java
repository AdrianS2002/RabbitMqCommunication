package com.example.DevicesMicroservices.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Setter
@Getter
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "description")
    private String description;

    @Column(name="address")
    private String address;

    @Column(name="consumption")
    private double consumption;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Device() {
    }

    public Device(String description, String address, double consumption, User id_user) {
        this.description = description;
        this.address = address;
        this.consumption = consumption;
        this.user = id_user;
    }
}
