package com.example.demo.service;

import com.example.demo.entity.Account;

public interface IAccountService {
    Account findAccountByUsername(String username);

    void saveOrUpdate(Account account);

    Account findAccountByEmail(String email);
}
