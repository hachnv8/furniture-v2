package com.hacheery.backend.service.impl;

import com.hacheery.backend.entity.Product;
import com.hacheery.backend.exception.FurnitureApiException;
import com.hacheery.backend.exception.ResourceNotFoundException;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.repository.CategoryRepository;
import com.hacheery.backend.repository.ProductRepository;
import com.hacheery.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


/**
 * Created by HachNV on 19/04/2023
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void createProduct(Product product) {
        if(productRepository.existsByName(product.getName())) {
            throw new FurnitureApiException(HttpStatus.BAD_REQUEST, "Sản phẩm này đã tồn tại");
        } else if(product.getCategoryId() == null || !categoryRepository.existsById(product.getCategoryId())) {
            throw new FurnitureApiException(HttpStatus.BAD_REQUEST, "Sản phẩm này phải thuộc một category");
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
        productRepository.save(p);
    }

    @Override
    public PagedResponse<Product> getProducts(String name, Pageable paging) {
        Page<Product> products;
        if(name == null) {
            products = productRepository.findAll(paging);
        } else {
            products = productRepository.findByNameContaining(name, paging);
        }
        List<Product> content = products.getNumberOfElements() == 0 ? Collections.emptyList() : products.getContent();
        return new PagedResponse<>(content, products.getNumber(), products.getSize(), products.getTotalElements(), products.getTotalPages());
    }

    @Override
    public ResponseEntity<Product> getProduct(Long productId) {
        Product product =productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public void updateProduct(Product product, Long productId) {
        if(!productRepository.existsById(productId)) {
            throw new FurnitureApiException(HttpStatus.BAD_REQUEST, "Sản phẩm này không tồn tại");
        }
        Product p = Product
                .builder()
                .price(product.getPrice())
                .categoryId(product.getCategoryId())
                .imageUrl(product.getImageUrl())
                .available(product.isAvailable())
                .description(product.getDescription())
                .name(product.getName())
                .build();
        productRepository.save(p);
    }

    @Override
    public void deleteProduct(Long productId) {
        if(!productRepository.existsById(productId)) {
            throw new FurnitureApiException(HttpStatus.BAD_REQUEST, "Sản phẩm này không tồn tại");
        }
        productRepository.deleteById(productId);
    }
}
