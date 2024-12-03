package com.example.MonitoringAndCommunicationMicroService.controller;

import com.example.MonitoringAndCommunicationMicroService.model.Measurements;
import com.example.MonitoringAndCommunicationMicroService.service.MeasurementsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;

    public MeasurementsController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<List<Object>> getMeasurementsForDevice(@PathVariable Long deviceId) {
        List<Measurements> measurements = measurementsService.getMeasurementsByDevice(deviceId);

        // Format the timestamp in each measurement
        List<Object> formattedMeasurements = measurements.stream().map(measurement -> {
            return Map.of(
                    "measurementId", measurement.getMeasurementId(),
                    "timestamp", Instant.ofEpochMilli(measurement.getTimestamp())
                            .atZone(ZoneId.of("Europe/Bucharest"))
                            .toLocalDateTime()
                            .toString(), // Human-readable timestamp
                    "consumption", measurement.getConsumption(),
                    "device", measurement.getDevice()
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(formattedMeasurements);
    }

}
