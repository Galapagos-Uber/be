package com.capstone.galapagosUber.mapper;

import com.capstone.galapagosUber.domain.entity.Vehicle;
import com.openapi.gen.springboot.dto.CreateVehicleRequestDto;
import com.openapi.gen.springboot.dto.UpdateVehicleRequestDto;
import com.openapi.gen.springboot.dto.VehicleResponseDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public abstract class VehicleMapper {

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "toOffsetDateTime")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", qualifiedByName = "toOffsetDateTime")
    public abstract VehicleResponseDto toVehicleResponseDto(Vehicle vehicle);

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", qualifiedByName = "toLocalDateTime")
    @InheritInverseConfiguration(name = "toVehicleResponseDto")
    @Mapping(target = "id", ignore = true)
    public abstract Vehicle toVehicle(VehicleResponseDto vehicleResponseDto);

    public abstract Vehicle toVehicle(CreateVehicleRequestDto createVehicleRequestDto);

    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract void partialUpdate(@MappingTarget Vehicle vehicle, UpdateVehicleRequestDto updateVehicleRequestDto);
}
