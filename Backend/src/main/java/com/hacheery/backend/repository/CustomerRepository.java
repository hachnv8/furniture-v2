package com.hacheery.backend.repository;

import com.hacheery.backend.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HachNV on 24/04/2023
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findByNameContaining(String name, Pageable paging);
}
