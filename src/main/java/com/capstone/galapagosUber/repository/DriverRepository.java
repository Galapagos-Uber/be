package com.capstone.galapagosUber.repository;

import java.util.Optional;
import java.util.UUID;

import com.capstone.galapagosUber.domain.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, UUID> {
    Optional<Driver> findByEmail(String email);
    Optional<Driver> findByPhoneNumber(String phoneNumber);
    Optional<Driver> findById(UUID uuid);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
