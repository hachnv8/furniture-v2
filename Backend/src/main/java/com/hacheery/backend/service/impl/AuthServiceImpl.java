package com.hacheery.backend.service.impl;

import com.hacheery.backend.payload.request.LoginRequest;
import com.hacheery.backend.payload.request.SignupRequest;
import com.hacheery.backend.payload.response.AuthResponse;
import com.hacheery.backend.repository.UserRepository;
import com.hacheery.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by HachNV on 25/04/2023
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        return null;
    }

    @Override
    public Boolean register(SignupRequest signupRequest) {
        SignupRequest request = new SignupRequest();
        request.setEmail(signupRequest.getEmail());

        return null;
    }
}
