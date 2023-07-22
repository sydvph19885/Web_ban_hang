package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import com.example.demo.utili.UploadImg;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.UUID;

@Controller
@MultipartConfig
public class LoginController {

    @Autowired
    private UploadImg uploadImg;

    @Autowired
    private IAccountService accountService;

    @GetMapping("/login-form")
    public String viewLogin() {
        return "view/dang-nhap";
    }


    @PostMapping("/user/register")
    public String createAccount(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "emailDK") String emailDK,
            @RequestParam(value = "passDK") String passDK,
            @RequestParam(value = "image") MultipartFile file,
            Model model
    ) {
        Account accountByEmail = accountService.findAccountByEmail(emailDK);
        if (accountService.findAccountByUsername(username) == null && accountByEmail == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Account account = new Account();
            account.setMatKhau(passwordEncoder.encode(passDK));
            account.setRole("CLIENT");
            account.setEmail(emailDK);
            account.setImage(file.getOriginalFilename());
            account.setUsername(username);
            account.setMa(UUID.randomUUID().toString());
            uploadImg.uploadImg(file);
            accountService.saveOrUpdate(account);
            model.addAttribute("thongBao", "Tạo tài khoản thành công!");
        } else {
            model.addAttribute("thongBao", "User Name hoặc Email đã tồn tại!");
        }
        return "view/dang-nhap";
    }

    @GetMapping("/user/forgot-pass")
    public String viewForgotPass() {
        return "view/forgot-pass";
    }

    @GetMapping("/user/xac-thuc")
    public String viewOtp() {
        return "view/otp-form";
    }
}
