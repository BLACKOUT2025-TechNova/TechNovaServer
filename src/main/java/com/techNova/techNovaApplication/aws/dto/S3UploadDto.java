package com.techNova.techNovaApplication.aws.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;

@Getter
public class S3UploadDto {

    private Long mobilityId;
    private MultipartFile file; // 파일명 = 사진 key랑 거의 동일
    private URI parkingPhotoUri;
    private String parkingPhotoKey;
}
