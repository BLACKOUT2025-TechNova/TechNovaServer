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
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "evaluation")
    private Evaluation evaluation;

    public ParkingStatus(Long mobilityId, float latitude, float longitude, Evaluation evaluation) {
        this.mobilityId = mobilityId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.evaluation = evaluation;
    }
}
