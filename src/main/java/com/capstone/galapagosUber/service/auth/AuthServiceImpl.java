package com.capstone.galapagosUber.service.auth;

import com.capstone.galapagosUber.domain.entity.Driver;
import com.capstone.galapagosUber.domain.entity.Rider;
import com.capstone.galapagosUber.repository.DriverRepository;
import com.capstone.galapagosUber.repository.RiderRepository;
import com.capstone.galapagosUber.utils.JwtUtil;
import com.openapi.gen.springboot.dto.JwtResponseDto;
import com.openapi.gen.springboot.dto.SignInRequestDto;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RiderRepository riderRepository;
    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * Authenticates a user (rider or driver) based on email and password.
     *
     * @param signInRequestDto The sign-in request containing email and password.
     * @return JwtResponseDto containing the JWT token.
     */
    @Override
    public JwtResponseDto authenticateUser(SignInRequestDto signInRequestDto) {
        String email = signInRequestDto.getEmail();
        String password = signInRequestDto.getPassword();

        Rider rider = riderRepository.findByEmail(email).orElse(null);
        if (rider != null) {
            if (passwordEncoder.matches(password, rider.getPassword())) {
                String token = jwtUtil.generateJwtToken(rider.getId(), "rider");
                log.info(token);
                return new JwtResponseDto().accessToken(token);
            } else {
                throw new BadRequestException("Invalid password for rider.");
            }
        }

        Driver driver = driverRepository.findByEmail(email).orElse(null);
        if (driver != null) {
            if (passwordEncoder.matches(password, driver.getPassword())) {
                String token = jwtUtil.generateJwtToken(driver.getId(), "driver");
                return new JwtResponseDto().accessToken(token);
            } else {
                throw new BadRequestException("Invalid password for driver.");
            }
        }

        throw new BadRequestException("User not found with the provided email.");
    }
}
