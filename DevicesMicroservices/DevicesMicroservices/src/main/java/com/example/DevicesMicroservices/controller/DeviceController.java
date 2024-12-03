package com.example.DevicesMicroservices.controller;

import com.example.DevicesMicroservices.dto.DeviceCreationDto;
import com.example.DevicesMicroservices.dto.DeviceDto;
import com.example.DevicesMicroservices.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")

@CrossOrigin
@Validated
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // Endpoint pentru a crea un nou dispozitiv
    @PostMapping("/create")
    public ResponseEntity<?> createDevice(@RequestBody DeviceCreationDto deviceCreationDto) {
       return ResponseEntity.ok(deviceService.addDevice(deviceCreationDto));
    }

    // Endpoint pentru a obține toate dispozitivele
    @GetMapping
    public ResponseEntity<?> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    // Endpoint pentru a obține un dispozitiv după ID
    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(deviceService.getDeviceById(id));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pentru a actualiza un dispozitiv
    @PutMapping("/update")
    public ResponseEntity<DeviceDto> updateDevice(@RequestBody DeviceDto deviceDto) {
        DeviceDto updatedDevice = deviceService.updateDevice(deviceDto);
        return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
    }

    // Endpoint pentru a șterge un dispozitiv
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
        try{
            deviceService.deleteDevice(id);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pentru a obține toate dispozitivele unui utilizator
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DeviceDto>> getDevicesByUser(@PathVariable Long userId) {
        List<DeviceDto> devices = deviceService.getAllDevicesByUser(userId);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
}
