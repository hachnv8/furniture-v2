package com.hacheery.backend.service;

import com.hacheery.backend.entity.Account;
import com.hacheery.backend.payload.response.PagedResponse;

/**
 * Created by HachNV on 25/04/2023
 */
public interface AccountService {
    void createAccount();
    void updateAccount();
    void deleteAccount();
    PagedResponse<Account> getAccounts();
}
