package com.capstone.galapagosUber.web.rest;

import com.openapi.gen.springboot.api.AuthApi;
import com.openapi.gen.springboot.api.AuthApiDelegate;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class AuthApiController implements AuthApi {

    private final AuthApiDelegate authApiDelegate;

    @Override
    public AuthApiDelegate getDelegate() {
        return authApiDelegate;
    }
}
