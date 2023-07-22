package com.example.demo.config;

import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustonUserDetailService implements UserDetailsService {

    @Autowired
    private IAccountService accountService;

    @Autowired
    HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Account account = accountService.findAccountByUsername(username);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(account.getRole());
        if (account != null && passwordEncoder.matches(request.getParameter("password"), account.getMatKhau())) {
            return new User(account.getUsername(), account.getMatKhau(), Collections.singleton(authority));
        } else {
            throw new UsernameNotFoundException("NOT FOUND USERNAME");
        }
    }
}
