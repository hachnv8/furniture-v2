package com.hacheery.backend.controller;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by HachNV on 17/04/2023
 */
@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/list/{categoryId}")
    public Optional<Category> getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @PostMapping("/create")
    public Category createCategory(Category category, @RequestParam(name = "parentId", required = false) Long parentId) {
        Category cat = Category.builder()
                        .name(category.getName())
                        .parentId(parentId)
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
