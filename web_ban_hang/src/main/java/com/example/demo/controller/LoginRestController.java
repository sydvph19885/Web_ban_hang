package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import com.example.demo.utili.SendMailForgot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class LoginRestController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private SendMailForgot sendMail;

    private String emailAddressSend;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @GetMapping("/check-mail/{email}")
    public ResponseEntity<?> checkMail(@PathVariable(value = "email") String email) {
        if (email != null) {
            Account account = accountService.findAccountByEmail(email);
            if (account != null) {
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/send-mail")
    public ResponseEntity<?> senMail(@RequestBody Account account) {
        var code = Integer.valueOf((int) ((Math.random() * 899999) + 100000));
        sendMail.sendMail("Xin chào: " + account.getUsername() + " " + "Code lấy lại pass: " + code, account.getEmail(), "Code Forgot Pass");
        account.setCode_mail(String.valueOf(code));
        emailAddressSend = account.getEmail();
        accountService.saveOrUpdate(account);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/xac-thuc-otp/{code}")
    public ResponseEntity<?> xacThuc(@PathVariable(value = "code") String code) {
        Account account = accountService.findAccountByEmail(emailAddressSend);
        Integer passNew = (int) ((Math.random() * 89999) + 10000);
        if (account.getCode_mail().equals(code)) {
            sendMail.sendMail("Mật khẩu mới của bạn là: " + passNew, account.getEmail(), "Mật khẩu mới!");
            account.setMatKhau(passwordEncoder.encode(String.valueOf(passNew)));
            accountService.saveOrUpdate(account);
            emailAddressSend = null;
            System.out.println("OKE");
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
