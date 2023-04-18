package com.hacheery.backend.service;

import com.hacheery.backend.entity.Category;

import java.util.List;
import java.util.Optional;

/**
 * Created by HachNV on 17/04/2023
 */
public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategory(Long categoryId);
    Category createCategory(Category category);
    Category updateCategory(Category category, Long categoryId);
    void deleteCategory(Long categoryId);
}
