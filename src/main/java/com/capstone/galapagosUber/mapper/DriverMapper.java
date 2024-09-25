package com.capstone.galapagosUber.mapper;

import com.capstone.galapagosUber.domain.entity.Driver;
import com.openapi.gen.springboot.dto.CreateDriverRequestDto;
import com.openapi.gen.springboot.dto.DriverResponseDto;
import com.openapi.gen.springboot.dto.UpdateDriverRequestDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class, DateTimeMapper.class})
public abstract class DriverMapper {

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "toOffsetDateTime")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", qualifiedByName = "toOffsetDateTime")
    @Mapping(target = "vehicleDetails", source = "vehicle")
    public abstract DriverResponseDto toDriverResponseDto(Driver driver);

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", qualifiedByName = "toLocalDateTime")
    @InheritInverseConfiguration(name = "toDriverResponseDto")
    @Mapping(target = "password", ignore = true)
    public abstract Driver toDriver(DriverResponseDto driverResponseDto);

    @Mapping(target = "vehicle", source = "vehicleDetails")
    public abstract Driver toDriver(CreateDriverRequestDto createDriverRequestDto);

    @Mapping(target = "vehicle", source = "vehicleDetails")
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract void partialUpdate(@MappingTarget Driver driver, UpdateDriverRequestDto updateDriverRequestDto);
}
