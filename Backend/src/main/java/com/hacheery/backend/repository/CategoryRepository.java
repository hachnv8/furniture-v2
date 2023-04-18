package com.hacheery.backend.repository;

import com.hacheery.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HachNV on 17/04/2023
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
