package com.techNova.techNovaApplication.parking.repository;

import com.techNova.techNovaApplication.parking.model.ParkingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingStatusRepository extends JpaRepository<ParkingStatus, Long> {
    Optional<ParkingStatus> findByMobilityId(Long id);
}
