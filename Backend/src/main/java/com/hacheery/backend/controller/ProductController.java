package com.hacheery.backend.controller;

import com.hacheery.backend.entity.Product;
import com.hacheery.backend.payload.response.ApiResponse;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.service.impl.ProductServiceImpl;
import com.hacheery.backend.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HachNV on 19/04/2023
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @PostMapping("/create")
    public ApiResponse createProduct(Product product) {
        productService.createProduct(product);
        return new ApiResponse(HttpStatus.OK, "Tạo sản phẩm thành công");
    }

    @GetMapping("/list")
    public PagedResponse<Product> getListProduct(
            @RequestParam(required = false) String name,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return productService.getProducts(name, paging);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/update/{productId}")
    public ApiResponse updateProduct(Product product, @PathVariable Long productId) {
        productService.updateProduct(product, productId);
        return new ApiResponse(HttpStatus.OK, "Cập nhật sản phẩm thành công");
    }

    @DeleteMapping("/delete/{productId}")
    public ApiResponse deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ApiResponse(HttpStatus.OK, "Xóa sản phẩm thành công");
    }
}
