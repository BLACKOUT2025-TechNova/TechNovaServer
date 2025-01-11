package com.techNova.techNovaApplication.user.controller;

import com.techNova.techNovaApplication.user.dto.HunterDto;
import com.techNova.techNovaApplication.user.dto.UserDto;
import com.techNova.techNovaApplication.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 로그인
     * @param dto
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto dto) {
        return userService.login(dto);
    }

    /**
     * 지쿠 헌트 후 리워드 지급
     */
    @PostMapping(value = "/hunt", consumes = "multipart/form-data")
    public ResponseEntity<String> reward(
            @RequestParam("phoneNumber") Long phoneNumber,
            @RequestParam("mobilityId") Long mobilityId,
            @RequestParam("parkingPhotoUri") String parkingPhotoUri,
            @RequestParam("parkingPhotoKey") String parkingPhotoKey,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        HunterDto dto = new HunterDto(phoneNumber, mobilityId, parkingPhotoUri, parkingPhotoKey);
        return userService.hunt(dto, file);
    }
}