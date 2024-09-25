package com.capstone.galapagosUber.web.rest;

import java.util.UUID;
import java.util.List;

import com.openapi.gen.springboot.dto.CreateDriverRequestDto;
import com.openapi.gen.springboot.dto.DriverResponseDto;
import com.openapi.gen.springboot.dto.UpdateDriverRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.openapi.gen.springboot.api.DriversApiDelegate;
import com.capstone.galapagosUber.service.driver.DriverService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DriversApiDelegateImpl implements DriversApiDelegate {

    private final DriverService driverService;

    @Override
    public ResponseEntity<DriverResponseDto> driversIdGet(UUID driverId) {
        DriverResponseDto driverResponseDto = driverService.getDriverById(driverId);
        return ResponseEntity.ok(driverResponseDto);
    }

    @Override
    public ResponseEntity<List<DriverResponseDto>> driversGet() {
        List<DriverResponseDto> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @Override
    public ResponseEntity<DriverResponseDto> driversPost(CreateDriverRequestDto createDriverRequestDto) {
        DriverResponseDto driverResponseDto = driverService.createDriver(createDriverRequestDto);
        return ResponseEntity.status(201).body(driverResponseDto);
    }

    @Override
    public ResponseEntity<DriverResponseDto> driversIdPut(UUID driverId, UpdateDriverRequestDto updateDriverRequestDto) {
        DriverResponseDto driverResponseDto = driverService.updateDriver(driverId, updateDriverRequestDto);
        return ResponseEntity.ok(driverResponseDto);
    }

    @Override
    public ResponseEntity<Void> driversIdDelete(UUID driverId) {
        driverService.deleteDriver(driverId);
        return ResponseEntity.noContent().build();
    }
}
