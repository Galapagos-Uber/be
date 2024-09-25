package com.capstone.galapagosUber.service.ride;

import java.util.UUID;
import com.capstone.galapagosUber.repository.RideRepository;
import com.capstone.galapagosUber.domain.entity.Ride;
import com.capstone.galapagosUber.web.rest.errors.EntityNotFoundException;
import com.openapi.gen.springboot.dto.CreateRideRequestDto;
import com.openapi.gen.springboot.dto.RideResponseDto;
import com.openapi.gen.springboot.dto.UpdateRideRequestDto;
import com.capstone.galapagosUber.mapper.RideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final RideMapper rideMapper;

    @Override
    public RideResponseDto createRide(CreateRideRequestDto createRideRequestDto) {
        Ride ride = rideMapper.toRide(createRideRequestDto);
        rideRepository.save(ride);
        return rideMapper.toRideResponseDto(ride);
    }

    @Override
    public void deleteRide(UUID id) {
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ride not found with id: " + id));
        rideRepository.delete(ride);
    }

    @Override
    public RideResponseDto getRideById(UUID id) {
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ride not found"));
        return rideMapper.toRideResponseDto(ride);
    }

    @Override
    public RideResponseDto updateRide(UUID id, UpdateRideRequestDto updateRideRequestDto) {
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ride not found"));
        rideMapper.partialUpdate(ride, updateRideRequestDto);
        rideRepository.save(ride);
        return rideMapper.toRideResponseDto(ride);
    }
}
