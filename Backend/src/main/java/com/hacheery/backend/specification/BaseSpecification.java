package com.hacheery.backend.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

/**
 * Created by HachNV on 26/04/2023
 */
public class BaseSpecification<T> {
    protected Specification<T> combine(Specification<T> spec1, Specification<T> spec2) {
        return (root, query, builder) -> builder.and(spec1.toPredicate(root, query, builder), spec2.toPredicate(root, query, builder));
    }

    protected Specification<T> likeIgnoreCase(String fieldName, String value) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%");
    }

    protected Specification<T> equal(String fieldName, Object value) {
        return (root, query, builder) -> builder.equal(root.get(fieldName), value);
    }
}
