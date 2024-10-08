package com.capstone.galapagosUber.web.rest;

import com.capstone.galapagosUber.service.auth.AuthService;
import com.openapi.gen.springboot.api.AuthApiDelegate;
import com.openapi.gen.springboot.dto.JwtResponseDto;
import com.openapi.gen.springboot.dto.SignInRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthApiDelegateImpl implements AuthApiDelegate {

    private final AuthService authService;

    @Override
    public ResponseEntity<JwtResponseDto> authSignInPost(SignInRequestDto signInRequestDto) {
        JwtResponseDto token = authService.authenticateUser(signInRequestDto);
        return ResponseEntity.ok(token);
    }
}