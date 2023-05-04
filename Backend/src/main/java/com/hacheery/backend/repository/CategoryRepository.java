package com.hacheery.backend.repository;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.payload.request.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by HachNV on 17/04/2023
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Page<Category> searchCategories(CategoryRequest request, Pageable pageable);
}
