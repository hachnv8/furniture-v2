package com.hacheery.backend.service;

import com.hacheery.backend.entity.Customer;
import com.hacheery.backend.payload.request.CustomerRequest;
import com.hacheery.backend.payload.response.PagedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


/**
 * Created by HachNV on 24/04/2023
 */
public interface CustomerService {
    PagedResponse<Customer> getCustomers(CustomerRequest request);
    ResponseEntity<Customer> getCustomer(Long productId);
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer, Long customerId);
    void deleteCustomer(Long customerId);
}
