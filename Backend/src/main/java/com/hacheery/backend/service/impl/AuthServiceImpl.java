package com.hacheery.backend.service.impl;

import com.hacheery.backend.entity.User;
import com.hacheery.backend.exception.FurnitureApiException;
import com.hacheery.backend.payload.UserProfile;
import com.hacheery.backend.payload.request.LoginRequest;
import com.hacheery.backend.payload.request.SignupRequest;
import com.hacheery.backend.payload.response.AuthResponse;
import com.hacheery.backend.repository.UserRepository;
import com.hacheery.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by HachNV on 25/04/2023
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        AuthResponse response = new AuthResponse();
        response.setAccessToken("ok");
        User user = userRepository.findByEmail(loginRequest.getEmail());
        UserProfile profile = UserProfile.builder()
                .username(user.getUsername())
                .role("ADMIN")
                .email(user.getEmail())
                .build();
        response.setUserProfile(profile);
        return response;
    }

    @Override
    public void register(SignupRequest signupRequest) {
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            throw new FurnitureApiException(HttpStatus.BAD_REQUEST, "Email này đã được đăng ký");
        }
        String email = signupRequest.getEmail();
        String username = signupRequest.getName();
        String password = signupRequest.getPassword();
        userRepository.save(new User(username, password, email));
    }
}
