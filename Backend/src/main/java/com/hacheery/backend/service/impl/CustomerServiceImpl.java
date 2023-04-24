package com.hacheery.backend.service.impl;

import com.hacheery.backend.entity.Customer;
import com.hacheery.backend.exception.FurnitureApiException;
import com.hacheery.backend.exception.ResourceNotFoundException;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.repository.CustomerRepository;
import com.hacheery.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public PagedResponse<Customer> getCustomers(String name, Pageable paging) {
        Page<Customer> customers;
        if (name == null) {
            customers = customerRepository.findAll(paging);
        } else {
            customers = customerRepository.findByNameContaining(name, paging);
        }
        List<Customer> content = customers.getNumberOfElements() == 0 ? Collections.emptyList() : customers.getContent();
        return new PagedResponse<>(content, customers.getNumber(), customers.getSize(), customers.getTotalElements(), customers.getTotalPages());
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
