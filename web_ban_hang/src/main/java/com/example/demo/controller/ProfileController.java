package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import com.example.demo.utili.UploadImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private UploadImg uploadImg;

    @GetMapping("/change-pass")
    public String viewChangePass(Model model, Principal principal) {
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);
            return "view/change-pass";
        } else {
            model.addAttribute("nullAccount", principal);
            return "view/dang-nhap";
        }

    }

    @PostMapping("/change-password")
    public String updatePass(Model model,
                             Principal principal,
                             @RequestParam(value = "currentPass") String currentPass,
                             @RequestParam(value = "newPass") String newPass,
                             @RequestParam(value = "confirmPass") String confirmPass
    ) {
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            model.addAttribute("account", account);
            if (passwordEncoder.matches(currentPass, account.getMatKhau())) {
                if (confirmPass.equals(newPass)) {
                    account.setMatKhau(passwordEncoder.encode(confirmPass));
                    accountService.saveOrUpdate(account);
                    model.addAttribute("thongBao", "Cập nhật mật khẩu thành công!");
                    return "view/dang-nhap";
                } else {
                    model.addAttribute("thongBao", "Xác nhận pass thất bại!");
                    return "view/change-pass";
                }
            } else {
                model.addAttribute("thongBao", "Mật khẩu cũ không đúng!");

                return "view/change-pass";
            }

        } else {
            model.addAttribute("nullAccount", principal);
            return "view/dang-nhap";
        }
    }

    @GetMapping("/profile")
    public String viewProfile(Principal principal, Model model) {
        Account account = accountService.findAccountByUsername(principal.getName());
        if (account != null) {
            model.addAttribute("account", account);
        }
        return "view/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(Principal principal, @RequestParam(name = "email") String email, @RequestParam(name = "sdt") String sdt, @RequestParam(name = "image") MultipartFile image) {
        Account account = accountService.findAccountByUsername(principal.getName());
        account.setImage(image.getOriginalFilename());
        account.setEmail(email);
        account.setSdt(sdt);
        uploadImg.uploadImg(image);
        accountService.saveOrUpdate(account);
        return "redirect:/profile";
    }
}
