package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.ChiTietSP;
import com.example.demo.service.IAccountService;
import com.example.demo.service.IChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class HomController {

    @Autowired
    private IChiTietSanPhamService sanPhamService;

    @Autowired
    private IAccountService accountService;

    @GetMapping("/home")
    public String viewHome(Principal principal, Model model, @RequestParam(value = "page", required = false) Optional<Integer> trangSo) {
        Pageable pageable = PageRequest.of(trangSo.orElse(0), 4);
        Page<ChiTietSP> chiTietSPPage = sanPhamService.getAll(pageable);
        model.addAttribute("listSanPham", chiTietSPPage.getContent());
        model.addAttribute("trangHT", chiTietSPPage.getNumber());
        model.addAttribute("tongTrang", chiTietSPPage.getTotalPages());
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);
            model.addAttribute("thongBao", "Cập nhật thành công!");
        } else {
            model.addAttribute("nullAccount", principal);
        }

        return "view/home";
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable(value = "id") String id, Model model) {
        ChiTietSP chiTietSP = sanPhamService.getOne(id);
        model.addAttribute("detail", chiTietSP);
        return "/view/detail";
    }

    @GetMapping("/contact")
    public String contact(Principal principal, Model model) {
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);
            model.addAttribute("thongBao", null);
        } else {
            model.addAttribute("nullAccount", principal);
        }
        return "/view/contac";
    }
}
