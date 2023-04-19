package com.hacheery.backend.controller;

import com.hacheery.backend.entity.Product;
import com.hacheery.backend.payload.response.ApiResponse;
import com.hacheery.backend.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
