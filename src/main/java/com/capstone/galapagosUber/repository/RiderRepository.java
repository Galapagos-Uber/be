package com.capstone.galapagosUber.repository;

import java.util.Optional;
import java.util.UUID;

import com.capstone.galapagosUber.domain.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, UUID> {
    Optional<Rider> findByEmail(String email);
    Optional<Rider> findByPhoneNumber(String phoneNumber);
    Optional<Rider> findById(UUID uuid);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
