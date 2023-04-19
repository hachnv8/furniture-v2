package com.hacheery.backend.repository;

import com.hacheery.backend.entity.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HachNV on 19/04/2023
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(@NotBlank String name);
}
