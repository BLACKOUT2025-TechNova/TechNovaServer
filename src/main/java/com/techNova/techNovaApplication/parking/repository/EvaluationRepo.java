package com.techNova.techNovaApplication.parking.repository;

import com.techNova.techNovaApplication.parking.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepo extends JpaRepository<Evaluation, Long> {
}
