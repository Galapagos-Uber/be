package com.capstone.galapagosUber.mapper;

import com.capstone.galapagosUber.domain.entity.Ride;
import com.capstone.galapagosUber.domain.enumeration.RideStatus;
import com.openapi.gen.springboot.dto.CreateRideRequestDto;
import com.openapi.gen.springboot.dto.RideResponseDto;
import com.openapi.gen.springboot.dto.UpdateRideRequestDto;
import jakarta.ws.rs.BadRequestException;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {RiderMapper.class, DriverMapper.class, VehicleMapper.class, DateTimeMapper.class})
public abstract class RideMapper {

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "toOffsetDateTime")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", qualifiedByName = "toOffsetDateTime")
    @Mapping(target = "pickupTime", source = "pickupTime", qualifiedByName = "toOffsetDateTime")
    @Mapping(target = "dropoffTime", source = "dropoffTime", qualifiedByName = "toOffsetDateTime")

    @Mapping(target = "rider", source = "rider")
    @Mapping(target = "driver", source = "driver")
    @Mapping(target = "vehicle", source = "vehicle")
    @Mapping(target = "fare", source = "fare")
    public abstract RideResponseDto toRideResponseDto(Ride ride);

    @Mapping(target = "fare", ignore = true)
    @Mapping(target = "rider", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "vehicle", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract Ride toRide(CreateRideRequestDto createRideRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "pickupTime", source = "pickupTime", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "dropoffTime", source = "dropoffTime", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "status", source="updateRideRequestDto.status", qualifiedByName = "toRideStatus")
    @Mapping(target = "fare", ignore = true)
    @Mapping(target = "rider", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "vehicle", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract void partialUpdate(@MappingTarget Ride ride, UpdateRideRequestDto updateRideRequestDto);

    @Named("toRideStatus")
    public RideStatus toRideStatus(String status) {
        if (status == null || status.isEmpty()) {
            return null;
        }
        try {
            return RideStatus.valueOf(status.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid status value: " + status);
        }
    }

}
