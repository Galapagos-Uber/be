package com.capstone.galapagosUber.service.ride;

import java.util.List;
import java.util.UUID;
import com.openapi.gen.springboot.dto.CreateRideRequestDto;
import com.openapi.gen.springboot.dto.RideResponseDto;
import com.openapi.gen.springboot.dto.UpdateRideRequestDto;

public interface RideService {
    List<RideResponseDto> getAllRides();

    RideResponseDto createRide(CreateRideRequestDto createRideRequestDto);

    void deleteRide(UUID id);

    RideResponseDto getRideById(UUID id);

    RideResponseDto updateRide(UUID id, UpdateRideRequestDto updateRideRequestDto);

    List<RideResponseDto> getRidesByRiderId(UUID riderId);

    List<RideResponseDto> getRidesByDriverId(UUID driverId);

}
