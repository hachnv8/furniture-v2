package com.hacheery.backend.specification;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.payload.request.CategoryRequest;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by HachNV on 04/05/2023
 */
public class CategorySpecification {
    public static Specification<Category> searchCategories(CategoryRequest request) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (request.getName() != null) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }
            if (request.getDescription() != null) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("description")), "%" + request.getDescription().toLowerCase() + "%"));
            }
            if (request.getParentId() != null) {
                Join<Category, Category> parentJoin = root.join("parent", JoinType.LEFT);
                predicate = cb.and(predicate, cb.equal(parentJoin.get("id"), request.getParentId()));
            }
            return predicate;
        };
    }
}
