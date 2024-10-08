package com.capstone.galapagosUber.service.auth;

import com.openapi.gen.springboot.dto.JwtResponseDto;
import com.openapi.gen.springboot.dto.SignInRequestDto;

public interface AuthService {
    JwtResponseDto authenticateUser(SignInRequestDto signInRequestDto);
}
