package com.capstone.galapagosUber.web.rest;

import java.util.List;
import java.util.UUID;

import com.capstone.galapagosUber.repository.RideRepository;
import com.openapi.gen.springboot.dto.CreateRideRequestDto;
import com.openapi.gen.springboot.dto.UpdateRideRequestDto;
import com.openapi.gen.springboot.dto.RideResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.openapi.gen.springboot.api.RidesApiDelegate;
import com.capstone.galapagosUber.service.ride.RideService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RidesApiDelegateImpl implements RidesApiDelegate {

    private final RideService rideService;

    @Override
    public ResponseEntity<List<RideResponseDto>> ridesGet() {
        List<RideResponseDto> rides = rideService.getAllRides();
        return ResponseEntity.ok(rides);
    }

    @Override
    public ResponseEntity<Void> ridesIdDelete(UUID rideId) {
        rideService.deleteRide(rideId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RideResponseDto> ridesIdGet(UUID rideId) {
        RideResponseDto rideResponseDto = rideService.getRideById(rideId);
        return ResponseEntity.ok(rideResponseDto);
    }

    @Override
    public ResponseEntity<RideResponseDto> ridesPost(CreateRideRequestDto createRideRequestDto) {
        RideResponseDto rideResponseDto = rideService.createRide(createRideRequestDto);
        return ResponseEntity.status(201).body(rideResponseDto);
    }

    @Override
    public ResponseEntity<RideResponseDto> ridesIdPut(UUID rideId, UpdateRideRequestDto updateRideRequestDto) {
        RideResponseDto rideResponseDto = rideService.updateRide(rideId, updateRideRequestDto);
        return ResponseEntity.ok(rideResponseDto);
    }
}
