package com.capstone.galapagosUber.service.ride;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.capstone.galapagosUber.domain.entity.Driver;
import com.capstone.galapagosUber.domain.entity.Rider;
import com.capstone.galapagosUber.domain.entity.Vehicle;
import com.capstone.galapagosUber.domain.enumeration.RideStatus;
import com.capstone.galapagosUber.repository.DriverRepository;
import com.capstone.galapagosUber.repository.RideRepository;
import com.capstone.galapagosUber.domain.entity.Ride;
import com.capstone.galapagosUber.repository.RiderRepository;
import com.capstone.galapagosUber.repository.VehicleRepository;
import com.capstone.galapagosUber.web.rest.errors.EntityNotFoundException;
import com.openapi.gen.springboot.dto.CreateRideRequestDto;
import com.openapi.gen.springboot.dto.RideResponseDto;
import com.openapi.gen.springboot.dto.UpdateRideRequestDto;
import com.capstone.galapagosUber.mapper.RideMapper;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final VehicleRepository vehicleRepository;
    private final RideMapper rideMapper;

    @Override
    public List<RideResponseDto> getAllRides() {
        return rideRepository.findAll().stream()
                .map(rideMapper::toRideResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RideResponseDto createRide(CreateRideRequestDto createRideRequestDto) {
        UUID riderId = UUID.fromString(String.valueOf(createRideRequestDto.getRiderId()));
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new BadRequestException("Rider not found with id " + riderId));

        Ride ride = rideMapper.toRide(createRideRequestDto);
        ride.setStatus(RideStatus.valueOf("Requested"));
        ride.setRider(rider);
        return rideMapper.toRideResponseDto(rideRepository.save(ride));
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

        if (updateRideRequestDto.getDriverId() != null) {
            UUID driverId;
            try {
                driverId = updateRideRequestDto.getDriverId();
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid driverId format: " + updateRideRequestDto.getDriverId());
            }

            Driver driver = driverRepository.findById(driverId)
                    .orElseThrow(() -> new BadRequestException("Driver not found with id: " + driverId));
            ride.setDriver(driver);
        }

//        if (updateRideRequestDto.getVehicleId() != null) {
//            UUID vehicleId;
//            try {
//                vehicleId = updateRideRequestDto.getVehicleId();
//            } catch (IllegalArgumentException e) {
//                throw new BadRequestException("Invalid vehicleId format: " + updateRideRequestDto.getVehicleId());
//            }
//
//            Vehicle vehicle = vehicleRepository.findById(vehicleId)
//                    .orElseThrow(() -> new BadRequestException("Vehicle not found with id: " + vehicleId));
//            ride.setVehicle(vehicle);
//        }

        rideMapper.partialUpdate(ride, updateRideRequestDto);
        rideRepository.save(ride);
        return rideMapper.toRideResponseDto(ride);
    }
}
