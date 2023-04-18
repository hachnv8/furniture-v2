package com.hacheery.backend.service.impl;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.repository.CategoryRepository;
import com.hacheery.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by HachNV on 17/04/2023
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public Page<Category> getAllCategories(Pageable paging) {
        return repository.findAll(paging);
    }

    @Override
    public Page<Category> findByNameContaining(String name, Pageable paging) {
        return repository.findByName(name, paging);
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
