package com.techNova.techNovaApplication.user.controller;

import com.techNova.techNovaApplication.user.dto.HunterDto;
import com.techNova.techNovaApplication.user.dto.UserDto;
import com.techNova.techNovaApplication.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/hunt")
    public ResponseEntity<String> reward(@RequestBody HunterDto dto) {
        return userService.hunt(dto);
    }
}