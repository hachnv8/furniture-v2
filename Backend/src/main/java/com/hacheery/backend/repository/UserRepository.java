package com.hacheery.backend.repository;

import com.hacheery.backend.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HachNV on 25/04/2023
 */
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(@NotBlank String email);

    User findByEmail(@NotBlank String email);
}
