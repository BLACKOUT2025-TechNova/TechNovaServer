package com.techNova.techNovaApplication.parking.dto;

import com.techNova.techNovaApplication.parking.model.Evaluation;
import com.techNova.techNovaApplication.parking.model.MobilityType;
import com.techNova.techNovaApplication.parking.model.ParkedMobilities;
import com.techNova.techNovaApplication.parking.model.ParkingStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;

import java.net.URI;
import java.util.Map;

@Builder
@Getter
public class MobilityDto {
    private long id;
    private MobilityType type;
    private Boolean needToBeHunted; // true 면 헌팅이 필요한 것! (= 잘못 놓여진 것)
    private String comment; // 한줄평
    private int score;
    private float latitude;
    private float longitude;
    private URI photoUri;
    private String photoKey;
    private Map<String, ScoreAndDetailDto> evaluation;

    public static MobilityDto toDto(ParkedMobilities mobility) {
        return MobilityDto.builder()
                .id(mobility.getId())
                .type(mobility.getType())
                .needToBeHunted(mobility.getNeedToBeHunted())
                .longitude(mobility.getLongitude())
                .comment(mobility.getComment())
                .score(mobility.getSum())
                .latitude(mobility.getLatitude())
                .photoUri(mobility.getPhotoUri())
                .photoKey(mobility.getPhotoKey())
                .evaluation(mobility.getParkingStatus().getEvaluation().getEvaluations())
                .build();
    }
}
