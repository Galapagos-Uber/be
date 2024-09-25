package com.capstone.galapagosUber.web.rest;

import com.openapi.gen.springboot.api.RidesApi;
import com.openapi.gen.springboot.api.RidesApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class RidesApiController implements RidesApi {

    private final RidesApiDelegate ridesApiDelegate;

    @Override
    public RidesApiDelegate getDelegate() {
        return ridesApiDelegate;
    }
}
