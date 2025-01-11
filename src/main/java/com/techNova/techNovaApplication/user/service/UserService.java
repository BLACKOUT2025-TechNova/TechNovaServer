package com.techNova.techNovaApplication.user.service;

import com.techNova.techNovaApplication.parking.model.ParkedMobilities;
import com.techNova.techNovaApplication.parking.repository.ParkedMobilityRepository;
import com.techNova.techNovaApplication.user.dto.HunterDto;
import com.techNova.techNovaApplication.user.dto.UserDto;
import com.techNova.techNovaApplication.user.model.UserEntity;
import com.techNova.techNovaApplication.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ParkedMobilityRepository mobilityRepository;

    public ResponseEntity<String> login(UserDto dto) {

        if (userRepository.findById(dto.getEmail()).isEmpty()) {
            userRepository.save(dto.toEntity());
            return ResponseEntity.ok("{\"message\": \"[회원가입 성공]\"}");
        }
        // 이미 저장된 유저면 AccessMode만 업데이트
        UserEntity user = userRepository.findById(dto.getEmail()).get();
        user.setAccessMode(dto.getAccessMode());
        return ResponseEntity.ok("{\"message\": \"[로그인 성공]\"}");
    }

    /**
     * 유저 리워드 지급,
     * ParkedMobilities에서 주차 상태 : 엉망 으로 표시된 필드를 정상으로 고치기
     * @param dto
     * @return
     */
    public ResponseEntity<String> hunt(HunterDto dto) {
        Optional<UserEntity> userById = userRepository.findById(dto.getEmail());
        Optional<ParkedMobilities> mobById = mobilityRepository.findById(dto.getMobilityId());
        if (userById.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\": \"[회원 정보 없음]\"}");
        }
        if (mobById.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\": \"[주차 정보 없음]\"}");
        }
        ParkedMobilities mobility = mobById.get();
        String evaluation = mobility.getParkingStatus().getEvaluation();
        if (evaluation.equals("mess")) { // 인게 있으면 모두 good으로?
            mobility.getParkingStatus().setEvaluation("good");
        }

        userById.get().setReward();

        return ResponseEntity.ok("{\"message\": \"[헌팅 성공]\"}");
    }
}
