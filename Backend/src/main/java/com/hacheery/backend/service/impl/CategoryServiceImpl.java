package com.hacheery.backend.service.impl;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.repository.CategoryRepository;
import com.hacheery.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by HachNV on 17/04/2023
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> getCategory(Long categoryId) {
        return repository.findById(categoryId);
    }

    @Override
    public Category createCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        return repository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        repository.deleteById(categoryId);
    }
}
