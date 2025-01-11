package com.techNova.techNovaApplication.parking.repository;

import com.techNova.techNovaApplication.parking.model.ParkedMobilities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkedMobilityRepository extends JpaRepository<ParkedMobilities, Long> {
}
