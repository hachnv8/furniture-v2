package com.hacheery.backend.service;

import com.hacheery.backend.entity.Product;
import com.hacheery.backend.payload.response.PagedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


/**
 * Created by HachNV on 19/04/2023
 */
public interface ProductService {
    void createProduct(Product product);
    PagedResponse<Product> getProducts(String name, Pageable paging);
    ResponseEntity<Product> getProduct(Long productId);
    void updateProduct(Product product, Long productId);
    void deleteProduct(Long productId);
}
