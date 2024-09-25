package com.capstone.galapagosUber.web.rest;

import com.openapi.gen.springboot.api.RidersApi;
import com.openapi.gen.springboot.api.RidersApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class RidersApiController implements RidersApi {

    private final RidersApiDelegate ridersApiDelegate;

    @Override
    public RidersApiDelegate getDelegate() {
        return ridersApiDelegate;
    }
}

