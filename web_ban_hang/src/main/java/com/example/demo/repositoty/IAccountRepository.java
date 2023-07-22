package com.example.demo.repositoty;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String> {
    Account findAccountByUsername(String username);

    Account findAccountByEmail(String email);
}
