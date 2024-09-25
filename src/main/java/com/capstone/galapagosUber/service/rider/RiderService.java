package com.capstone.galapagosUber.service.rider;

import java.util.UUID;
import java.util.List;

import com.openapi.gen.springboot.dto.CreateRiderRequestDto;
import com.openapi.gen.springboot.dto.RiderResponseDto;
import com.openapi.gen.springboot.dto.UpdateRiderRequestDto;

public interface RiderService {

    RiderResponseDto createRider(CreateRiderRequestDto createRiderRequestDto);

    RiderResponseDto getRiderById(UUID id);

    List<RiderResponseDto> getAllRiders();

    RiderResponseDto updateRider(UUID id, UpdateRiderRequestDto updateRiderRequestDto);

    void deleteRider(UUID id);
}
