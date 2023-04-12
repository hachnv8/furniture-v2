package com.hacheery.backend.security.service.impl;

import com.hacheery.backend.payload.request.LoginRequest;
import com.hacheery.backend.payload.request.SignupRequest;
import com.hacheery.backend.payload.response.JwtAuthResponse;
import com.hacheery.backend.security.repository.UserRepository;
import com.hacheery.backend.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by HachNV on 12/04/2023
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtAuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Boolean register(SignupRequest signupRequest) {
        return null;
    }
}
