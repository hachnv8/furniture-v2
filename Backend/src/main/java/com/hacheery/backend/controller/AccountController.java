package com.hacheery.backend.controller;

import com.hacheery.backend.entity.Account;
import com.hacheery.backend.payload.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HachNV on 25/04/2023
 */
@RestController
@RequestMapping("/account")
public class AccountController {
//    @PostMapping("/create")
//    public ApiResponse createAccount(Account account) {
//        return new ApiResponse(HttpStatus.OK, "Tạo tài khoản thành công");
//    }
}
