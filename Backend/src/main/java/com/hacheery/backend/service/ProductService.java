package com.hacheery.backend.service;

import com.hacheery.backend.entity.Product;
import com.hacheery.backend.payload.response.PagedResponse;

import java.awt.print.Pageable;

/**
 * Created by HachNV on 19/04/2023
 */
public interface ProductService {
    Boolean createProduct(Product product);
    PagedResponse<Product> getProducts(String name, Pageable paging);
    Product updateProduct(Product product, Long productId);
    void deleteProduct(Long productId);
}
