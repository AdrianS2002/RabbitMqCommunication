package com.example.MonitoringAndCommunicationMicroService.config;

import com.example.MonitoringAndCommunicationMicroService.model.Device;
import com.example.MonitoringAndCommunicationMicroService.model.Measurements;
import com.example.MonitoringAndCommunicationMicroService.repository.DeviceRepository;
import com.example.MonitoringAndCommunicationMicroService.repository.MeasurementsRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeasurementListener {

    private final MeasurementsRepository measurementsRepository;
    private final DeviceRepository deviceRepository;
    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final List<Double> consumptionBuffer = new ArrayList<>();

    public MeasurementListener(MeasurementsRepository measurementsRepository, DeviceRepository deviceRepository, SimpMessagingTemplate messagingTemplate) {
        this.measurementsRepository = measurementsRepository;
        this.deviceRepository = deviceRepository;
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @RabbitListener(queues = "measurementQueue")
    @Transactional
    public void receiveMeasurement(String message) {
        try {
            // Deserialize the message to Message object
            Message receivedMessage = objectMapper.readValue(message, Message.class);

            // Fetch the corresponding Device from the database
            Device device = deviceRepository.findById(receivedMessage.getDeviceId())
                    .orElseThrow(() -> new RuntimeException("Device not found"));

            // Add the measurement value to the buffer
            consumptionBuffer.add(receivedMessage.getMeasurementValue());

            // If 6 values are collected, calculate the average
            if (consumptionBuffer.size() == 6) {
                double average = consumptionBuffer.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

                // Save the average as a measurement in the database
                Measurements newMeasurement = new Measurements(
                        System.currentTimeMillis(),
                        average,
                        device
                );
                measurementsRepository.save(newMeasurement);
                System.out.println("Saved average measurement: " + newMeasurement);

                // Check if the average exceeds the consumption threshold
                if (average > device.getHourConsumption()) {
                    String alert = String.format("Alert: Device %d exceeded threshold! Average Consumption: %.2f > %.2f",
                            device.getDeviceId(),
                            average,
                            device.getHourConsumption());

                    // Send alert via WebSocket
                    messagingTemplate.convertAndSend("/topic/alerts", alert);
                    System.out.println("WebSocket Alert Sent: " + alert);
                }

                // Clear the buffer after processing
                consumptionBuffer.clear();
            }
        } catch (Exception e) {
            System.err.println("Error processing measurement: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


//    @RabbitListener(queues = "deviceChangesQueue")
//    @Transactional
//    public void receiveDeviceChangeMessage(String message) {
//        try {
//            // Deserialize the device change message
//            DeviceChangeEvent event = objectMapper.readValue(message, DeviceChangeEvent.class);
//
//            switch (event.getOperation().toLowerCase()) {
//                case "create":
//                    // Handle device creation
//                    Device newDevice = new Device(event.getDeviceId(), 100.0, 3L); // Example default values
//                    deviceRepository.save(newDevice);
//                    System.out.println("Device created: " + newDevice);
//                    break;
//
//                case "update":
//                    // Handle device update
//                    Device existingDevice = deviceRepository.findById(event.getDeviceId())
//                            .orElseThrow(() -> new IllegalArgumentException("Device not found for update: " + event.getDeviceId()));
//                    // Example update logic (update consumption threshold, etc.)
//                    existingDevice.setHourConsumption(150.0); // Updated value
//                    deviceRepository.save(existingDevice);
//                    System.out.println("Device updated: " + existingDevice);
//                    break;
//
//                case "delete":
//                    // Handle device deletion
//                    deviceRepository.deleteById(event.getDeviceId());
//                    System.out.println("Device deleted: " + event.getDeviceId());
//                    break;
//
//                default:
//                    System.err.println("Unknown operation: " + event.getOperation());
//            }
//        } catch (Exception e) {
//            System.err.println("Error processing device change message: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
