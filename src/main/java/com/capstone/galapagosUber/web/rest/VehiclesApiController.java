package com.capstone.galapagosUber.web.rest;

import com.openapi.gen.springboot.api.VehiclesApi;
import com.openapi.gen.springboot.api.VehiclesApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class VehiclesApiController implements VehiclesApi {

    private final VehiclesApiDelegate vehiclesApiDelegate;

    @Override
    public VehiclesApiDelegate getDelegate() {
        return vehiclesApiDelegate;
    }
}

