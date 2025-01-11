package com.techNova.techNovaApplication.user.dto;

import com.techNova.techNovaApplication.parking.dto.GptEvaluateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@Getter
@AllArgsConstructor
public class HunterDto {

    private Long phoneNumber;
    /**
     * 헌팅의 유효성을 검증하기 위한 필드들이 추가될 수도
     * ex) gpt를 통해 넘어온 헌팅 이후 사진 분석 결과
     */
    private Long mobilityId;
    private String parkingPhotoUri;
    private String parkingPhotoKey;
    private String comment;
    private List<GptEvaluateDto> evaluation;

}
