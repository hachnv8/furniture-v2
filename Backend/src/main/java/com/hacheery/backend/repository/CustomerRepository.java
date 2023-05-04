package com.hacheery.backend.repository;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.entity.Customer;
import com.hacheery.backend.payload.request.CustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by HachNV on 24/04/2023
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    Page<Customer> searchCustomers(CustomerRequest request, Pageable paging);
}
