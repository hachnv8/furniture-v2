package com.hacheery.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Created by HachNV on 12/04/2023
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private Long id;
    private String firstName;
    private String lastName;
    private Instant joinedAt;
    private String email;
    private String phone;
    private String role;
}
