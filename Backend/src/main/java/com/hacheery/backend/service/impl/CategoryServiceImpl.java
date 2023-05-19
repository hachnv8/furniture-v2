package com.hacheery.backend.service.impl;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.exception.ResourceNotFoundException;
import com.hacheery.backend.payload.request.CategoryRequest;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.repository.CategoryRepository;
import com.hacheery.backend.service.CategoryService;
import com.hacheery.backend.specification.CategorySpecification;
import com.hacheery.backend.utils.AppUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Created by HachNV on 17/04/2023
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public PagedResponse<Category> getCategories(CategoryRequest request) {
        AppUtils.validatePageNumberAndPageSize(request.getPage(), request.getSize());
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
                Sort.by(request.getSortDirection(), request.getSortBy()));
        Specification<Category> spec = CategorySpecification.searchCategories(request);
        Page<Category> categories = categoryRepository.findAll(spec, pageable);
        List<Category> content = categories.getNumberOfElements() == 0 ? Collections.emptyList() : categories.getContent();
        return new PagedResponse<>(content, categories.getNumber(), categories.getSize(), categories.getTotalElements(),
                categories.getTotalPages());
    }

    @Override
    public Category getCategory(Long categoryId) {
//        return categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(
//                String.format("Không tìm thấy danh mục với ID: '%d'", categoryId)
//        ));
    }

    @Override
    public Category createCategory(Category category) {
        validateCategory(category);

        try {
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi lưu danh mục vào cơ sở dữ liệu", ex);
        }
    }

    @Override
    @Transactional
    public Category updateCategory(Category category, Long categoryId) {
        validateCategoryId(categoryId);
        validateCategory(category);

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục với ID: " + categoryId));
        existingCategory.setName(category.getName());
        existingCategory.setParentId(category.getParentId());

        try {
            return categoryRepository.save(existingCategory);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi cập nhật danh mục vào cơ sở dữ liệu", ex);
        }
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        validateCategoryId(categoryId);

        try {
            categoryRepository.deleteById(categoryId);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi xóa danh mục khỏi cơ sở dữ liệu", ex);
        }
    }

    // logic function from here

    private void validateCategory(Category category) {
        Objects.requireNonNull(category, "Thông tin danh mục không được để trống");
        validateCategoryName(category.getName());
        validateParentCategory(category.getParentId());
    }

    private void validateCategoryName(String categoryName) {
        // thay vì kiểm tra xem category.getName() == null && category.getName().isEmpty() không
        // có thể dùng hàm có sẵn StringUtils.isBlank(category.getName) để đơn giản hóa
        if (StringUtils.isBlank(categoryName)) {
            throw new IllegalArgumentException("Tên danh mục không được để trống");
        }
        if (categoryRepository.existsByName(categoryName)) {
            throw new IllegalArgumentException("Tên danh mục đã tồn tại");
        }
    }

    private void validateParentCategory(Long parentId) {
        if (parentId != null && !categoryRepository.existsById(parentId)) {
            throw new IllegalArgumentException("Không có danh mục cha trong cơ sở dữ liệu");
        }
    }

    private void validateCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", "Id", categoryId);
        }
    }
}
