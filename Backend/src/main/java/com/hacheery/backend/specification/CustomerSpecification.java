package com.hacheery.backend.specification;

import com.hacheery.backend.entity.Customer;
import com.hacheery.backend.payload.request.CustomerRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by HachNV on 04/05/2023
 */
public class CustomerSpecification {
    public static Specification<Customer> searchCustomer(CustomerRequest request) {
        return (root, query, cb) -> { 
            Predicate predicate = cb.conjunction();
            if (request.getName() != null) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }
            if (request.getAddress() != null) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("address")), "%" + request.getAddress().toLowerCase() + "%"));
            }
            if (request.getEmail() != null) {
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("email")), request.getEmail()));
            }
            if (request.getPhoneNumber() != null) {
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("phoneNumber")), request.getPhoneNumber()));
            }
            return predicate;
        };
    }
}
