package com.capstone.galapagosUber.web.rest;

import com.openapi.gen.springboot.api.DriversApi;
import com.openapi.gen.springboot.api.DriversApiDelegate;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class DriversApiController implements DriversApi {

    private final DriversApiDelegate driversApiDelegate;

    @Override
    public DriversApiDelegate getDelegate() {
        return driversApiDelegate;
    }
}
