package com.capstone.galapagosUber.repository;

import java.util.UUID;

import com.capstone.galapagosUber.domain.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
}
