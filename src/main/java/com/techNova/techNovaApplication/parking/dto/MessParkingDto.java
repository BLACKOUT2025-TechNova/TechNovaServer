package com.techNova.techNovaApplication.parking.dto;

import com.techNova.techNovaApplication.parking.model.ParkedMobilities;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MessParkingDto {
    private Long mobilityId;
    private float latitude;
    private float longitude;
    private String evaluation;

    public static List<MessParkingDto> toDtoList(List<ParkedMobilities> parkedMobilities) {
        return parkedMobilities.stream()
                .map(mobility -> new MessParkingDto(
                        mobility.getId(),
                        mobility.getParkingStatus().getLatitude(),
                        mobility.getParkingStatus().getLongitude(),
                        mobility.getParkingStatus().getEvaluation()
                ))
                .collect(Collectors.toList());
    }

}
