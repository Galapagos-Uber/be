package com.capstone.galapagosUber.mapper;

import com.capstone.galapagosUber.domain.entity.Rider;
import com.openapi.gen.springboot.dto.CreateRiderRequestDto;
import com.openapi.gen.springboot.dto.RiderResponseDto;
import com.openapi.gen.springboot.dto.UpdateRiderRequestDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public abstract class RiderMapper {

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "toOffsetDateTime")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", qualifiedByName = "toOffsetDateTime")
    public abstract RiderResponseDto toRiderResponseDto(Rider rider);

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", qualifiedByName = "toLocalDateTime")
    @InheritInverseConfiguration(name = "toRiderResponseDto")
    @Mapping(target = "password", ignore = true)
    public abstract Rider toRider(RiderResponseDto riderResponseDto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract Rider toRider(CreateRiderRequestDto createRiderRequestDto);

    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract void partialUpdate(@MappingTarget Rider rider, UpdateRiderRequestDto updateRiderRequestDto);
}
