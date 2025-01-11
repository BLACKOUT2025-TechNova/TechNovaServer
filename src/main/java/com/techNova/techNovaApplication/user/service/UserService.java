package com.techNova.techNovaApplication.user.service;

import com.techNova.techNovaApplication.parking.dto.GptEvaluateDto;
import com.techNova.techNovaApplication.parking.dto.ScoreAndDetailDto;
import com.techNova.techNovaApplication.parking.model.Evaluation;
import com.techNova.techNovaApplication.parking.model.ParkedMobilities;
import com.techNova.techNovaApplication.parking.repository.ParkedMobilityRepository;
import com.techNova.techNovaApplication.user.dto.HunterDto;
import com.techNova.techNovaApplication.user.dto.UserDto;
import com.techNova.techNovaApplication.user.model.AccessMode;
import com.techNova.techNovaApplication.user.model.UserEntity;
import com.techNova.techNovaApplication.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ParkedMobilityRepository mobilityRepository;

    @Transactional
    public ResponseEntity<String> login(UserDto dto) {

        if (userRepository.findById(String.valueOf(dto.getPhoneNumber())).isEmpty()) {
            userRepository.save(dto.toEntity());
            return ResponseEntity.ok("{\"message\": \"[회원가입 성공]\"}");
        }
        // 이미 저장된 유저면 AccessMode만 업데이트
        UserEntity user = userRepository.findById(String.valueOf(dto.getPhoneNumber())).get();
        user.setAccessMode(dto.getAccessMode());
        return ResponseEntity.ok("{\"message\": \"[로그인 성공]\"}");
    }

    /**
     * 유저 리워드 지급,
     * ParkedMobilities에서 주차 상태 : 엉망 으로 표시된 필드를 정상으로 고치기
     * @param dto
     * @return
     */
    @Transactional
    public ResponseEntity<String> hunt(HunterDto dto ) throws IOException {
        Optional<UserEntity> userById = userRepository.findById(String.valueOf(dto.getPhoneNumber()));
        Optional<ParkedMobilities> mobById = mobilityRepository.findById(dto.getMobilityId());

        if (userById.isEmpty()) return ResponseEntity.badRequest().body("{\"error\": \"[회원 정보 없음]\"}");
        if (mobById.isEmpty()) return ResponseEntity.badRequest().body("{\"error\": \"[주차 정보 없음]\"}");
        if (!ifUserIsHunter(userById.get())) return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body("{\"error\": \"[헌팅 권한 없음]\"}");

        // 모든 정보가 정상일 경우
        ParkedMobilities mobility = mobById.get();
        if (mobility.getNeedToBeHunted()) { // 해당 모빌리티가 헌팅 대상인 경우
            modifyContent(mobility, userById.get(), dto);
            return ResponseEntity.ok("{\"message\": \"[헌팅 성공]\"}");
        }
        //헌팅 대상이 아닌 경우
        return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST) // 400에러
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\" : \"[헌팅 대상이 아닙니다]\"}");

    }

    private void modifyContent(ParkedMobilities mobility, UserEntity user, HunterDto dto) throws IOException {
        List<GptEvaluateDto> gptList = dto.getEvaluation();
        HashMap<String, ScoreAndDetailDto> map = new HashMap<>();
        for (GptEvaluateDto gpt : gptList) {
            map.put(gpt.getCategory(), new ScoreAndDetailDto(gpt.getScore(), gpt.getDetail()));
        }
        Evaluation evaluation = new Evaluation(map);
        mobility.getParkingStatus().setEvaluation(evaluation); // evaluation을 변경

        mobility.setNeedToBeHunted(false); // 헌팅 대상에서 제외
        mobility.setComment(dto.getComment()); // 코멘트 변경
        mobility.setSum(calSum(evaluation)); // 총점 변경
        user.setReward(); // 리워드 지급
        try {
            mobility.setPhotoUri(new URI(dto.getParkingPhotoUri())); // 디비에 사진 정보 저장
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mobility.setPhotoKey(dto.getParkingPhotoKey());
    }

    private int calSum(Evaluation evaluation) {
        int sum = 0;
        for (ScoreAndDetailDto scoreAndDetailDto : evaluation.getEvaluations().values()) {
            sum += scoreAndDetailDto.getScore();
        }
        return sum;
    }

    private boolean ifUserIsHunter(UserEntity user) {
        return user.getAccessMode().equals(AccessMode.HUNTER);
    }
}
