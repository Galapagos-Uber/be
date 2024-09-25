package com.capstone.galapagosUber.service.vehicle;

import java.util.UUID;
import com.capstone.galapagosUber.repository.VehicleRepository;
import com.capstone.galapagosUber.domain.entity.Vehicle;
import com.capstone.galapagosUber.web.rest.errors.EntityNotFoundException;
import com.openapi.gen.springboot.dto.CreateVehicleRequestDto;
import com.openapi.gen.springboot.dto.UpdateVehicleRequestDto;
import com.openapi.gen.springboot.dto.VehicleResponseDto;
import com.capstone.galapagosUber.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleResponseDto createVehicle(CreateVehicleRequestDto createVehicleRequestDto) {
        Vehicle vehicle = vehicleMapper.toVehicle(createVehicleRequestDto);
        vehicleRepository.save(vehicle);
        return vehicleMapper.toVehicleResponseDto(vehicle);
    }

    @Override
    public void deleteVehicle(UUID id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
        vehicleRepository.delete(vehicle);
    }

    @Override
    public VehicleResponseDto getVehicleById(UUID id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        return vehicleMapper.toVehicleResponseDto(vehicle);
    }

    @Override
    public VehicleResponseDto updateVehicle(UUID id, UpdateVehicleRequestDto updateVehicleRequestDto) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        vehicleMapper.partialUpdate(vehicle, updateVehicleRequestDto);
        vehicleRepository.save(vehicle);
        return vehicleMapper.toVehicleResponseDto(vehicle);
    }
}
