package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.NhaSanXuat;
import com.example.demo.service.IAccountService;
import com.example.demo.service.IChiTietSanPhamService;
import com.example.demo.service.IMauSacService;
import com.example.demo.service.INhaSanXuatService;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class HomController {

    @Autowired
    private IChiTietSanPhamService sanPhamService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IMauSacService mauSacService;

    @Autowired
    private INhaSanXuatService nhaSanXuatService;

    @GetMapping("/home")
    public String viewHome(Principal principal, Model model,
                           @RequestParam(value = "page", required = false) Optional<Integer> trangSo) {
        java.util.List<Integer> listSize = new ArrayList<>();
        Pageable pageable = PageRequest.of(trangSo.orElse(0), 6);
        Page<ChiTietSP> chiTietSPPage = sanPhamService.getAll(pageable);
        model.addAttribute("listSanPham", chiTietSPPage.getContent());
        model.addAttribute("trangHT", chiTietSPPage.getNumber());
        model.addAttribute("tongTrang", chiTietSPPage.getTotalPages());
        model.addAttribute("mauSac", mauSacService.getAll());
        model.addAttribute("nsx", nhaSanXuatService.getAll());
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);
            model.addAttribute("thongBao", "Cập nhật thành công!");
        } else {
            model.addAttribute("nullAccount", principal);
        }

        return "view/home";
    }

    @GetMapping("/search")
    public String search(Principal principal, Model model, @RequestParam(name = "tenSearch") String tenSearch, @RequestParam(value = "page", required = false) Optional<Integer> trangSo) {
        Pageable pageable = PageRequest.of(trangSo.orElse(0), 6);
        Page<ChiTietSP> chiTietSPPage = sanPhamService.findChiTietSPByTenSanPhamContains(tenSearch, pageable);
        model.addAttribute("listSanPham", chiTietSPPage.getContent());
        model.addAttribute("trangHT", chiTietSPPage.getNumber());
        model.addAttribute("tongTrang", chiTietSPPage.getTotalPages());
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);
            model.addAttribute("thongBao", "Cập nhật thành công!");
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

    @GetMapping("/search-multiple")
    public String searchDaThuocTinh(
            @RequestParam(value = "namBH", required = false) Integer namBH,
            @RequestParam(value = "mauSac", required = false) MauSac mauSac,
            @RequestParam(value = "nsx", required = false) NhaSanXuat nhaSanXuat,
            @RequestParam(value = "voucher", required = false) Integer voucher,
            @RequestParam(value = "size", required = false) Integer size,
            Model model,
            Principal principal
    ) {
        java.util.List<Integer> listSize = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 6);
        Page<ChiTietSP> listSearch = sanPhamService.searchDaThuocTinh(namBH, mauSac, nhaSanXuat, voucher, size, pageable);
        model.addAttribute("listSanPham", listSearch.getContent());
        model.addAttribute("trangHT", listSearch.getNumber());
        model.addAttribute("tongTrang", listSearch.getTotalPages());
        model.addAttribute("mauSac", mauSacService.getAll());
        model.addAttribute("nsx", nhaSanXuatService.getAll());
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);
            model.addAttribute("thongBao", "Cập nhật thành công!");
        } else {
            model.addAttribute("nullAccount", principal);
        }

        return "view/home";
    }

}
