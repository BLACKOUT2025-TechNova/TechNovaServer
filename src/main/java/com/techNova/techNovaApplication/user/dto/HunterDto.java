package com.techNova.techNovaApplication.user.dto;

import lombok.Getter;

@Getter
public class HunterDto {

    private String email;
    /**
     * 헌팅의 유효성을 검증하기 위한 필드들이 추가될 수도
     * ex) gpt를 통해 넘어온 헌팅 이후 사진 분석 결과
     */
    private Long mobilityId;
}
