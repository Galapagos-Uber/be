package com.capstone.galapagosUber.service.vehicle;

import java.util.UUID;
import com.openapi.gen.springboot.dto.CreateVehicleRequestDto;
import com.openapi.gen.springboot.dto.UpdateVehicleRequestDto;
import com.openapi.gen.springboot.dto.VehicleResponseDto;

public interface VehicleService {

    VehicleResponseDto createVehicle(CreateVehicleRequestDto createVehicleRequestDto);

    void deleteVehicle(UUID id);

    VehicleResponseDto getVehicleById(UUID id);

    VehicleResponseDto updateVehicle(UUID id, UpdateVehicleRequestDto updateVehicleRequestDto);
}
