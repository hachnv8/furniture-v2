package com.hacheery.backend.controller;

import com.hacheery.backend.entity.Category;
import com.hacheery.backend.entity.Customer;
import com.hacheery.backend.payload.request.CustomerRequest;
import com.hacheery.backend.payload.response.ApiResponse;
import com.hacheery.backend.payload.response.PagedResponse;
import com.hacheery.backend.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HachNV on 24/04/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerServiceImpl customerService;

    @GetMapping("/list")
    public ResponseEntity<PagedResponse<Customer>> getCustomers(CustomerRequest request) {
        PagedResponse<Customer> response = customerService.getCustomers(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping("/create")
    public ApiResponse createCustomer(Customer customer) {
        customerService.createCustomer(customer);
        return new ApiResponse(HttpStatus.OK, "Tạo khách hàng thành công");
    }

    @PutMapping("/update/{customerId}")
    public ApiResponse updateCustomer(Customer customer, @PathVariable Long customerId) {
        customerService.updateCustomer(customer, customerId);
        return new ApiResponse(HttpStatus.OK, "Cập nhật thông tin khách hàng thành công");
    }

    @DeleteMapping("/delete/{customerId}")
    public ApiResponse deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ApiResponse(HttpStatus.OK, "Xóa khách hàng thành công");
    }
}
