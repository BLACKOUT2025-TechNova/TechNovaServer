package com.techNova.techNovaApplication.parking.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.net.URI;

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
    private Boolean needToBeHunted; // true 면 헌팅이 필요한 것! (= 잘못 놓여진 것)
    private float latitude;
    private float longitude;
    private URI photoUri;
    private String photoKey;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parking_status", unique = true)
    private ParkingStatus parkingStatus;
}
