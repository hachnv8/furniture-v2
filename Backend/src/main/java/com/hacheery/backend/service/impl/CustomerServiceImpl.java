package com.hacheery.backend.service.impl;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.entity.Customer;
import com.hacheery.backend.exception.FurnitureApiException;
import com.hacheery.backend.exception.ResourceNotFoundException;
import com.hacheery.backend.payload.request.CustomerRequest;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.repository.CustomerRepository;
import com.hacheery.backend.service.CustomerService;
import com.hacheery.backend.specification.CategorySpecification;
import com.hacheery.backend.specification.CustomerSpecification;
import com.hacheery.backend.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by HachNV on 24/04/2023
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public PagedResponse<Customer> getCustomers(CustomerRequest request) {
        AppUtils.validatePageNumberAndPageSize(request.getPage(), request.getSize());
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
                Sort.by(request.getSortDirection(), request.getSortBy()));
        Specification<Customer> spec = CustomerSpecification.searchCustomer(request);
        Page<Customer> categories = customerRepository.findAll(spec, pageable);
        List<Customer> content = categories.getNumberOfElements() == 0 ? Collections.emptyList() :categories.getContent();
        return new PagedResponse<>(content, categories.getNumber(), categories.getSize(), categories.getTotalElements(),
                categories.getTotalPages());
    }

    @Override
    public ResponseEntity<Customer> getCustomer(Long customerId) {
        Customer cust =  customerRepository.findById(customerId).orElseThrow(() ->  new ResourceNotFoundException("Customer", "Id", customerId));
        return new ResponseEntity<>(cust, HttpStatus.OK);
    }

    @Override
    public void createCustomer(Customer customer) {
        Customer cust = new Customer();
        cust.setName(customer.getName());
        cust.setAddress(customer.getAddress());
        cust.setEmail(customer.getEmail());
        cust.setPhoneNumber(cust.getPhoneNumber());
        customerRepository.save(cust);
    }

    @Override
    public void updateCustomer(Customer customer, Long customerId) {
        if(!customerRepository.existsById(customerId)) {
            throw new FurnitureApiException(HttpStatus.BAD_REQUEST, "Khách hàng này không tồn tại");
        }
        Customer cust = new Customer();
        cust.setName(customer.getName());
        cust.setEmail(customer.getEmail());
        cust.setAddress(customer.getAddress());
        cust.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(cust);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
