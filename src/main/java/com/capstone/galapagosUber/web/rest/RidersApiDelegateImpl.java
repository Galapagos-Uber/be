package com.capstone.galapagosUber.web.rest;

import java.util.UUID;
import java.util.List;

import com.openapi.gen.springboot.dto.CreateRiderRequestDto;
import com.openapi.gen.springboot.dto.RiderResponseDto;
import com.openapi.gen.springboot.dto.UpdateRiderRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.openapi.gen.springboot.api.RidersApiDelegate;
import com.capstone.galapagosUber.service.rider.RiderService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RidersApiDelegateImpl implements RidersApiDelegate {

    private final RiderService riderService;

    @Override
    public ResponseEntity<RiderResponseDto> ridersIdGet(UUID riderId) {
        RiderResponseDto riderResponseDto = riderService.getRiderById(riderId);
        return ResponseEntity.ok(riderResponseDto);
    }

    @Override
    public ResponseEntity<List<RiderResponseDto>> ridersGet() {
        List<RiderResponseDto> riders = riderService.getAllRiders();
        return ResponseEntity.ok(riders);
    }

    @Override
    public ResponseEntity<RiderResponseDto> ridersPost(CreateRiderRequestDto createRiderRequestDto) {
        RiderResponseDto riderResponseDto = riderService.createRider(createRiderRequestDto);
        return ResponseEntity.status(201).body(riderResponseDto);
    }

    @Override
    public ResponseEntity<RiderResponseDto> ridersIdPut(UUID riderId, UpdateRiderRequestDto updateRiderRequestDto) {
        RiderResponseDto riderResponseDto = riderService.updateRider(riderId, updateRiderRequestDto);
        return ResponseEntity.ok(riderResponseDto);
    }

    @Override
    public ResponseEntity<Void> ridersIdDelete(UUID riderId) {
        riderService.deleteRider(riderId);
        return ResponseEntity.noContent().build();
    }
}
