package com.hacheery.backend.controller;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.payload.request.CategoryRequest;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HachNV on 17/04/2023
 */
@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public ResponseEntity<PagedResponse<Category>> getAllCategories(@ModelAttribute CategoryRequest request) {
        PagedResponse<Category> response = categoryService.getCategories(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/create")
    public Category createCategory(Category category) {
        Category cat = Category.builder()
                .name(category.getName())
                .parentId(category.getParentId())
                .build();
        return categoryService.createCategory(cat);
    }

    @PutMapping("/update/{categoryId}")
    public Category updateCategory(Category category, @PathVariable Long categoryId) {
        // cần check xem ở đây nếu lấy trực tiếp id từ category có được không, hay cần phải lấy @PathVariable Long categoryId
        Category cat = Category.builder()
                .name(category.getName())
                .build();
        return categoryService.updateCategory(cat, categoryId);
    }

    @PostMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
