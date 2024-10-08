package com.capstone.galapagosUber.repository;

import java.util.UUID;
import java.util.List;

import com.capstone.galapagosUber.domain.entity.Driver;
import com.capstone.galapagosUber.domain.entity.Ride;
import com.capstone.galapagosUber.domain.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, UUID> {
    List<Ride> findByRider(Rider rider);
    List<Ride> findByDriver(Driver driver);
}
