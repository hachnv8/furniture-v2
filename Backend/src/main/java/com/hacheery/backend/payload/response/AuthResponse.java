package com.hacheery.backend.payload.response;

import com.hacheery.backend.payload.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by HachNV on 25/04/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private UserProfile userProfile;
}
