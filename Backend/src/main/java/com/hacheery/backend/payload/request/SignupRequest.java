package com.hacheery.backend.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Created by HachNV on 12/04/2023
 */
@Data
public class SignupRequest {
    @NotBlank
    @Size(min=4,max=50)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=6,max=20)
    private String password;
}
