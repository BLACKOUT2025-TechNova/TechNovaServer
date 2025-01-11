package com.techNova.techNovaApplication.parking.controller;

import com.techNova.techNovaApplication.parking.dto.ParkingRequestDto;
import com.techNova.techNovaApplication.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            ){
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

}
