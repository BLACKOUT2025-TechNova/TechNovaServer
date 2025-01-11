package com.techNova.techNovaApplication.parking.service;

import com.techNova.techNovaApplication.parking.dto.MessParkingDto;
import com.techNova.techNovaApplication.parking.dto.MobilityDto;
import com.techNova.techNovaApplication.parking.dto.ParkingRequestDto;
import com.techNova.techNovaApplication.parking.model.MobilityType;
import com.techNova.techNovaApplication.parking.model.ParkedMobilities;
import com.techNova.techNovaApplication.parking.model.ParkingStatus;
import com.techNova.techNovaApplication.parking.repository.ParkedMobilityRepository;
import com.techNova.techNovaApplication.parking.repository.ParkingStatusRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private final ParkedMobilityRepository mobilityRepository;
    private final ParkingStatusRepository statusRepository;

    public ResponseEntity<String> park(ParkingRequestDto dto) throws URISyntaxException {
        ParkingStatus status = new ParkingStatus(
                dto.getId(), dto.getLatitude(), dto.getLongitude(), dto.getEvaluation());

        ParkedMobilities mobility = ParkedMobilities.builder()
                .id(dto.getId())
                .type(dto.getType())
                .needToBeHunted(clarifyNeedForHunting(dto))
                .photoUri(new URI(dto.getParkingPhotoUri()))
                .photoKey(dto.getParkingPhotoKey())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .parkingStatus(status)
                .build();

        statusRepository.save(status);
        mobilityRepository.save(mobility);

        return ResponseEntity.ok("{\"message\": \"주차 성공\"}");
    }

    private boolean clarifyNeedForHunting(ParkingRequestDto dto) {
        if (dto.getEvaluation().equals("mess")) {
            return true;
        }
        return false;
    }


    /**
     * 유저가 모빌리티를 타려고 하면, status repo에서 삭제하고, 주차된 모빌리티 repo에서도 삭제한다.
     * @param id
     * @return
     */
    public ResponseEntity<String> unpark(Long id) {

        Optional<ParkedMobilities> byId = mobilityRepository.findById(id);

        if (byId.isEmpty()) {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\" : \"[이동 수단 확인 불가]\"}");
        }
        ParkedMobilities parkedMobilities = byId.get();
        mobilityRepository.delete(parkedMobilities);

        return ResponseEntity.ok("{\"message\": \"모빌리티 활성화 성공\"}");
    }

    public ResponseEntity<List<MessParkingDto>> getMessParkingMobilities() {
        List<ParkedMobilities> parkedMobilities = mobilityRepository.findByNeedToBeHuntedTrue();
        if (parkedMobilities.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(MessParkingDto.toDtoList(parkedMobilities));
    }

    public ResponseEntity<MobilityDto> findByMobilityId(Long id) {
        Optional<ParkedMobilities> byId = mobilityRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null);
        }
        ParkedMobilities mobility = byId.get();
        MobilityDto dto = MobilityDto.toDto(mobility);
        return ResponseEntity.ok(dto);
    }
}
