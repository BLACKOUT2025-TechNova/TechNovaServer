package com.techNova.techNovaApplication.aws.controller;

import com.techNova.techNovaApplication.aws.service.LambdaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lambda")
@RequiredArgsConstructor
public class LambdaController {

    private final LambdaService lambdaService;

    @PostMapping("/invoke")
    public ResponseEntity<String> invokeLambda(
            @RequestBody String payload
    ) {
        String functionName = "blackout20-s3-backend";
        return ResponseEntity.ok(lambdaService.invokeLambda(functionName, payload));
    }
}
