package com.techNova.techNovaApplication.parking.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ScoreAndDetailDto {
    private int score;
    private String detail;
}
