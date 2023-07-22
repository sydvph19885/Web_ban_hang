package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class ThongKeController {

    @Autowired
    private IAccountService accountService;

    @GetMapping("/thong-ke")
    public String viewThongKe(Principal principal, Model model) {
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);
        }
        return "view/thong-ke";
    }

    @GetMapping("/top-10-long-stock")
    public String top10StockIn(Principal principal, Model model) {
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);
        }
        return "view/top-10-stock-in";
    }
}
