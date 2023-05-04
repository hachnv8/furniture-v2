package com.hacheery.backend.service.impl;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.exception.ResourceNotFoundException;
import com.hacheery.backend.payload.request.CategoryRequest;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.repository.CategoryRepository;
import com.hacheery.backend.service.CategoryService;
import com.hacheery.backend.specification.CategorySpecification;
import com.hacheery.backend.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by HachNV on 17/04/2023
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public PagedResponse<Category> getCategories(CategoryRequest request) {
        AppUtils.validatePageNumberAndPageSize(request.getPage(), request.getSize());
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
                Sort.by(request.getSortDirection(), request.getSortBy()));
        Specification<Category> spec = CategorySpecification.searchCategories(request);
        Page<Category> categories = repository.findAll(spec, pageable);
        List<Category> content = categories.getNumberOfElements() == 0 ? Collections.emptyList() :categories.getContent();
        return new PagedResponse<>(content, categories.getNumber(), categories.getSize(), categories.getTotalElements(),
                categories.getTotalPages());
    }

    @Override
    public Category getCategory(Long categoryId) {
        return repository.findById(categoryId).orElseThrow(() ->  new ResourceNotFoundException("Category", "Id", categoryId) );
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
