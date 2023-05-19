package com.hacheery.backend.controller;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.payload.request.CategoryRequest;
import com.hacheery.backend.payload.response.ApiResponse;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

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
    public ResponseEntity<ApiResponse<Category>> createCategory(Category category) {
        ApiResponse<Category> response = new ApiResponse<>();
        Category savedCategory = categoryService.createCategory(category);
        // Log the successful creation
        logger.info("Tạo thành công category với ID: " + savedCategory.getId());
        response.setSuccess(true);
        response.setMessage("Tạo category thành công!");
        response.setData(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @PutMapping("/update/{categoryId}")
//    public ApiResponse updateCategory(Category category, @PathVariable Long categoryId) {
//        // cần check xem ở đây nếu lấy trực tiếp id từ category có được không, hay cần phải lấy @PathVariable Long categoryId
//        // đối với rest api, để thuận tiện cho người dùng thì khi click vào 1 sản phẩm ta có link như sau:
//        // http://localhost:8080/book/9783827319333
//        // lúc này nên sử dụng @PathVariable để hứng id
//        categoryService.updateCategory(category, categoryId);
//        return new ApiResponse(HttpStatus.OK, "Cập nhật category thành công");
//    }
//
//    @PostMapping("/delete/{categoryId}")
//    public ApiResponse deleteCategory(@PathVariable Long categoryId) {
//        categoryService.deleteCategory(categoryId);
//        return new ApiResponse(HttpStatus.OK, "Xóa category thành công");
//    }
}
