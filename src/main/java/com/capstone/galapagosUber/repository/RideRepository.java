package com.capstone.galapagosUber.repository;

import java.util.UUID;
import java.util.List;

import com.capstone.galapagosUber.domain.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, UUID> {
    List<Ride> findByRiderId(UUID riderId);
    List<Ride> findByDriverId(UUID driverId);
}
