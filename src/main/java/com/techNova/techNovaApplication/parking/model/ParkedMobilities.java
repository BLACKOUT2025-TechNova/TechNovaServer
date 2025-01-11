package com.techNova.techNovaApplication.parking.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkedMobilities {

    @Id
    private long id;
    private MobilityType type;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parking_status", unique = true)
    private ParkingStatus parkingStatus;

}
