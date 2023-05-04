package com.hacheery.backend.service;

import com.hacheery.backend.payload.request.LoginRequest;
import com.hacheery.backend.payload.request.SignupRequest;
import com.hacheery.backend.payload.response.AuthResponse;

/**
 * Created by HachNV on 25/04/2023
 */
public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    void register(SignupRequest signupRequest);
}
