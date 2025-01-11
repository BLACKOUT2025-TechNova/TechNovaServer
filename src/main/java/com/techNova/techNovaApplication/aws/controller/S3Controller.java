package com.techNova.techNovaApplication.aws.controller;

import com.techNova.techNovaApplication.aws.dto.S3UploadDto;
import com.techNova.techNovaApplication.aws.service.S3Service;
import com.techNova.techNovaApplication.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestBody S3UploadDto dto
    ) throws IOException {
        MultipartFile file = dto.getFile();
        try {
            String bucketName = "blackout-20-bucket";
            s3Service.uploadFile(bucketName, file.getOriginalFilename(), file.getInputStream(), file.getSize());
            return ResponseEntity.ok("{\"message\": \"[파일 업로드 성공]\"}");
        } catch (Exception e) {
            throw e;
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"[파일 업로드 실패]\"}");
        }
    }

    @GetMapping("/download/{fileName}") // 파일 이름을 다 스쿠터 id로 하자 (mobilityId)
    public ResponseEntity<byte[]> downloadFile(
            @PathVariable String fileName
    ) throws IOException {

        try {
            String bucketName = "blackout-20-bucket";
            InputStream inputStream = s3Service.downloadFile(bucketName, fileName);
            byte[] content = inputStream.readAllBytes(); // 파일 내용을 바이트 배열로 읽기

            String contentType = "image/jpeg"; // 파일 타입에 따라 변경
            if (fileName.endsWith(".png")) {
                contentType = "image/png";
            } else if (fileName.endsWith(".gif")) {
                contentType = "image/gif";
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(content);
        } catch (Exception e) {
            throw e;
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
