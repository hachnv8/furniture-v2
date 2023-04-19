package com.hacheery.backend.controller;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.service.impl.CategoryServiceImpl;
import com.hacheery.backend.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by HachNV on 17/04/2023
 */
@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;
//    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/list")
    public PagedResponse<Category> getAllCategories(
            @RequestParam(required = false) String name,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return categoryService.getCategories(name, paging);
    }

    @GetMapping("/{categoryId}")
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
