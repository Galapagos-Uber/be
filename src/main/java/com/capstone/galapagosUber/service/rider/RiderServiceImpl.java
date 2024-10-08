package com.capstone.galapagosUber.service.rider;

import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

import com.capstone.galapagosUber.repository.RiderRepository;
import com.capstone.galapagosUber.domain.entity.Rider;
import com.capstone.galapagosUber.web.rest.errors.EntityNotFoundException;
import com.capstone.galapagosUber.mapper.RiderMapper;
import com.openapi.gen.springboot.dto.CreateRiderRequestDto;
import com.openapi.gen.springboot.dto.UpdateRiderRequestDto;
import com.openapi.gen.springboot.dto.RiderResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RiderServiceImpl implements RiderService {

    private final RiderRepository riderRepository;
    private final RiderMapper riderMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RiderResponseDto createRider(CreateRiderRequestDto createRiderRequestDto) {
        Rider rider = riderMapper.toRider(createRiderRequestDto);
        String hashedPassword = passwordEncoder.encode(createRiderRequestDto.getPassword());
        rider.setPassword(hashedPassword);
        rider.setIsActive("True");

        riderRepository.save(rider);
        return riderMapper.toRiderResponseDto(rider);
    }

    @Override
    public void deleteRider(UUID id) {
        Rider rider = riderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rider not found with id: " + id));
        riderRepository.delete(rider);
    }

    @Override
    public RiderResponseDto getRiderById(UUID id) {
        Rider rider = riderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rider not found with id: " + id));
        return riderMapper.toRiderResponseDto(rider);
    }

    @Override
    public List<RiderResponseDto> getAllRiders() {
        return riderRepository.findAll().stream()
                .map(riderMapper::toRiderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RiderResponseDto updateRider(UUID id, UpdateRiderRequestDto updateRiderRequestDto) {
        Rider rider = riderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rider not found with id: " + id));
        riderMapper.partialUpdate(rider, updateRiderRequestDto);
        riderRepository.save(rider);
        return riderMapper.toRiderResponseDto(rider);
    }
}
