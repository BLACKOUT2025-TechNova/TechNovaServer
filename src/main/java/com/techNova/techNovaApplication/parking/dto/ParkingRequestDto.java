package com.techNova.techNovaApplication.parking.dto;

import com.techNova.techNovaApplication.parking.model.MobilityType;
import lombok.Getter;

@Getter
public class ParkingRequestDto {
    private Long id; // 모빌리티의 아이디값
    private MobilityType type; // BIKE 혹은 SCOOTER 중 하나의 값이어야 함.

    // parking status 정보
    private float latitude;
    private float longitude;
    private String evaluation;
}
