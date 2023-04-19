package com.hacheery.backend.service.impl;

import com.hacheery.backend.entity.Product;
import com.hacheery.backend.exception.FurnitureApiException;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.repository.ProductRepository;
import com.hacheery.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

/**
 * Created by HachNV on 19/04/2023
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public Boolean createProduct(Product product) {
        // cách 1
//        if(repository.findByName(product.getName()) > 0) {
//            throw new FurnitureApiException(HttpStatus.BAD_REQUEST, "Sản phẩm này đã tồn tại");
//        }
        // cách 2
        if(repository.existsByName(product.getName())) {
            throw new FurnitureApiException(HttpStatus.BAD_REQUEST, "Sản phẩm này đã tồn tại");
        }
        Product p = Product
                .builder()
                .name(product.getName())
                .description(product.getDescription())
                .categoryId(product.getCategoryId())
                .available(product.isAvailable())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .build();
        repository.save(p);
        return true;
    }

    @Override
    public PagedResponse<Product> getProducts(String name, Pageable paging) {
        return null;
    }

    @Override
    public Product updateProduct(Product product, Long productId) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }
}
