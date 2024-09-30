package com.capstone.galapagosUber.web.rest;

import java.util.List;
import java.util.UUID;

import com.openapi.gen.springboot.dto.CreateVehicleRequestDto;
import com.openapi.gen.springboot.dto.DriverResponseDto;
import com.openapi.gen.springboot.dto.UpdateVehicleRequestDto;
import com.openapi.gen.springboot.dto.VehicleResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.openapi.gen.springboot.api.VehiclesApiDelegate;
import com.capstone.galapagosUber.service.vehicle.VehicleService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VehiclesApiDelegateImpl implements VehiclesApiDelegate {

    private final VehicleService vehicleService;

    @Override
    public ResponseEntity<List<VehicleResponseDto>> vehiclesGet() {
        List<VehicleResponseDto> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @Override
    public ResponseEntity<VehicleResponseDto> vehiclesIdGet(UUID vehicleId) {
        VehicleResponseDto vehicleResponseDto = vehicleService.getVehicleById(vehicleId);
        return ResponseEntity.ok(vehicleResponseDto);
    }

    @Override
    public ResponseEntity<VehicleResponseDto> vehiclesPost(CreateVehicleRequestDto createVehicleRequestDto) {
        VehicleResponseDto vehicleResponseDto = vehicleService.createVehicle(createVehicleRequestDto);
        return ResponseEntity.status(201).body(vehicleResponseDto);
    }

    @Override
    public ResponseEntity<Void> vehiclesIdDelete(UUID vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<VehicleResponseDto> vehiclesIdPut(UUID vehicleId, UpdateVehicleRequestDto updateVehicleRequestDto) {
        VehicleResponseDto vehicleResponseDto = vehicleService.updateVehicle(vehicleId, updateVehicleRequestDto);
        return ResponseEntity.ok(vehicleResponseDto);
    }
}
