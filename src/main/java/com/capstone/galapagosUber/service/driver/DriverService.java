package com.capstone.galapagosUber.service.driver;

import java.util.UUID;
import java.util.List;

import com.openapi.gen.springboot.dto.CreateDriverRequestDto;
import com.openapi.gen.springboot.dto.DriverResponseDto;
import com.openapi.gen.springboot.dto.UpdateDriverRequestDto;

public interface DriverService {

    DriverResponseDto createDriver(CreateDriverRequestDto createDriverRequestDto);

    DriverResponseDto getDriverById(UUID id);

    List<DriverResponseDto> getAllDrivers();

    DriverResponseDto updateDriver(UUID id, UpdateDriverRequestDto updateDriverRequestDto);

    void deleteDriver(UUID id);
}
