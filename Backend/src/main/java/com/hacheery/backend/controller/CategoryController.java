package com.hacheery.backend.controller;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.service.impl.CategoryServiceImpl;
import com.hacheery.backend.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by HachNV on 17/04/2023
 */
@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllCategories(
            @RequestParam(required = false) String name,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
    ) {

        try {
            List<Category> categoryList;
            Pageable paging = PageRequest.of(page, size);
            Page<Category> categories;
            if(name == null) {
                categories = categoryService.getAllCategories(paging);
            } else {
                categories = categoryService.findByNameContaining(name, paging);
            }
            categoryList = categories.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("categories", categoryList);
            response.put("currentPage", categories.getNumber());
            response.put("totalItems", categories.getTotalElements());
            response.put("totalPages", categories.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.trace(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{categoryId}")
    public Optional<Category> getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategory(categoryId);
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
