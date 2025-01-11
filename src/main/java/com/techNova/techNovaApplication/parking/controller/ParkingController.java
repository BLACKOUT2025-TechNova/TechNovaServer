package com.techNova.techNovaApplication.parking.controller;

import com.techNova.techNovaApplication.parking.dto.MessParkingDto;
import com.techNova.techNovaApplication.parking.dto.MobilityDto;
import com.techNova.techNovaApplication.parking.dto.ParkingRequestDto;
import com.techNova.techNovaApplication.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    /**
     * 유저가 mobility를 주차를 하고 인증 사진을 찍으면
     * 프론트에서 사진을 감별한 후 백으로 정보를 보낸다
     */
    @PostMapping("/park")
    public ResponseEntity<String> park(
            @RequestBody ParkingRequestDto dto
    ) throws URISyntaxException {
        return parkingService.park(dto);
    }

    /**
     * 모빌리티를 유저가 다시 타는 경우
     * @param id
     * @return
     */
    @DeleteMapping("/unpark")
    public ResponseEntity<String> unpark(
            @RequestParam("mobilityId") Long id
    ) {
        return parkingService.unpark(id);
    }

    /**
     * 엉망인 주차 상태를 가진 모빌리티들을 모두 보여준다
     * @return
     */
    @GetMapping("/messy/all")
    public ResponseEntity<List<MessParkingDto>> findAllMessyMobilities() {
        return parkingService.getMessParkingMobilities();
    }

    @GetMapping("/find")
    public ResponseEntity<MobilityDto> findByMobilityId(
            @RequestParam("mobilityId") Long id
    ) {
        return parkingService.findByMobilityId(id);
    }

}
