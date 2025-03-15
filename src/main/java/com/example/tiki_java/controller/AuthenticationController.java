package com.example.tiki_java.controller;

import lombok.AccessLevel;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.example.tiki_java.core.ApiResponse;
import com.example.tiki_java.dto.request.LoginRequest;
import com.example.tiki_java.dto.response.LoginResponse;
import com.example.tiki_java.dto.request.RegisterRequest;
import com.example.tiki_java.service.AuthenticationService;
import com.example.tiki_java.dto.response.RegisterResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        log.info("Hello");
        return ApiResponse.<LoginResponse>builder()
                .message("Login successful")
                .result(authenticationService.login(request))
                .build();
    }

    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ApiResponse.<RegisterResponse>builder()
                .result(authenticationService.register(request))
                .build();
    }


}
