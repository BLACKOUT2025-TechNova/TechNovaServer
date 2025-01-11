package com.techNova.techNovaApplication.parking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
//@Builder
public class ParkingStatus {
    @Id @GeneratedValue
    private Long id;

    private Long mobilityId;
    private float latitude;
    private float longitude;
    private String evaluation; // 구조가 string 에서 바뀔 수도

    public ParkingStatus(Long mobilityId, float latitude, float longitude, String evaluation) {
        this.mobilityId = mobilityId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.evaluation = evaluation;
    }
}
