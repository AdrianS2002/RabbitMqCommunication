package com.example.DevicesMicroservices.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class DeviceCreationDto {
    @NotEmpty(message = "Description cannot be empty")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @NotEmpty(message = "Address cannot be empty")
    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    @Min(value = 0, message = "Consumption must be a positive number")
    private double consumption;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

}
