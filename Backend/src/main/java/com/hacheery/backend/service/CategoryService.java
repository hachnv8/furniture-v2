package com.hacheery.backend.service;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.payload.response.PagedResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created by HachNV on 17/04/2023
 */
public interface CategoryService {
    PagedResponse<Category> getCategories(String name, Pageable paging);
    Optional<Category> getCategory(Long categoryId);
    Category createCategory(Category category);
    Category updateCategory(Category category, Long categoryId);
    void deleteCategory(Long categoryId);
}
