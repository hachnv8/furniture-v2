package com.hacheery.backend.security.service;

import com.hacheery.backend.payload.request.LoginRequest;
import com.hacheery.backend.payload.request.SignupRequest;
import com.hacheery.backend.payload.response.JwtAuthResponse;

/**
 * Created by HachNV on 12/04/2023
 */
public interface AuthService {
    JwtAuthResponse login(LoginRequest loginRequest);
    Boolean register(SignupRequest signupRequest);
}
