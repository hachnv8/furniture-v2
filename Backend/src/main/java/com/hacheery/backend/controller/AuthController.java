package com.hacheery.backend.controller;

import com.hacheery.backend.entity.Account;
import com.hacheery.backend.payload.request.LoginRequest;
import com.hacheery.backend.payload.request.SignupRequest;
import com.hacheery.backend.payload.response.ApiResponse;
import com.hacheery.backend.payload.response.AuthResponse;
import com.hacheery.backend.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HachNV on 25/04/2023
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private AuthServiceImpl authService;

    @PostMapping("/signin")
    private ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    private ApiResponse register(@Valid @RequestBody SignupRequest signupRequest) {
        authService.register(signupRequest);
        return new ApiResponse(HttpStatus.OK, "Đăng ký tài khoản thành công");
    }
}
