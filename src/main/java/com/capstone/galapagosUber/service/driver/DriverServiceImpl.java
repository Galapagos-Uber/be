package com.capstone.galapagosUber.service.driver;

import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

import com.capstone.galapagosUber.repository.DriverRepository;
import com.capstone.galapagosUber.repository.VehicleRepository;
import com.capstone.galapagosUber.domain.entity.Driver;
import com.capstone.galapagosUber.domain.entity.Vehicle;
import com.capstone.galapagosUber.web.rest.errors.EntityNotFoundException;
import com.capstone.galapagosUber.mapper.DriverMapper;
import com.capstone.galapagosUber.mapper.VehicleMapper;
import com.openapi.gen.springboot.dto.CreateDriverRequestDto;
import com.openapi.gen.springboot.dto.UpdateDriverRequestDto;
import com.openapi.gen.springboot.dto.DriverResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverMapper driverMapper;
    private final VehicleMapper vehicleMapper;

    @Override
    public DriverResponseDto createDriver(CreateDriverRequestDto createDriverRequestDto) {
        Driver driver = driverMapper.toDriver(createDriverRequestDto);

        driver.setPassword(createDriverRequestDto.getPassword());
        driver.setIsActive("True");

        driverRepository.save(driver);
        return driverMapper.toDriverResponseDto(driver);
    }

    @Override
    public void deleteDriver(UUID id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + id));
        driverRepository.delete(driver);
    }

    @Override
    public DriverResponseDto getDriverById(UUID id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + id));
        return driverMapper.toDriverResponseDto(driver);
    }

    @Override
    public List<DriverResponseDto> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(driverMapper::toDriverResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public DriverResponseDto updateDriver(UUID id, UpdateDriverRequestDto updateDriverRequestDto) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + id));
        driverMapper.partialUpdate(driver, updateDriverRequestDto);

        if (updateDriverRequestDto.getVehicleDetails() != null) {
            Vehicle vehicle = driver.getVehicle();
            if (vehicle == null) {
                vehicle = new Vehicle();
                driver.setVehicle(vehicle);
            }
            vehicleMapper.partialUpdate(vehicle, updateDriverRequestDto.getVehicleDetails());
            vehicleRepository.save(vehicle);
        }

        driverRepository.save(driver);
        return driverMapper.toDriverResponseDto(driver);
    }
}
