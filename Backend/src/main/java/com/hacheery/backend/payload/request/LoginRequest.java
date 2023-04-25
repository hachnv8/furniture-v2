package com.hacheery.backend.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Created by HachNV on 25/04/2023
 */
@Data
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
