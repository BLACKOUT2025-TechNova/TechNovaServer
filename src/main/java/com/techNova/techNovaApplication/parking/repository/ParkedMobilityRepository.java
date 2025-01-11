package com.techNova.techNovaApplication.parking.repository;

import com.techNova.techNovaApplication.parking.model.ParkedMobilities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkedMobilityRepository extends JpaRepository<ParkedMobilities, Long> {
    List<ParkedMobilities> findByNeedToBeHuntedTrue();
}
