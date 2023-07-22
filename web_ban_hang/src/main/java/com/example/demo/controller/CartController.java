package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.service.IAccountService;
import com.example.demo.service.IChiTietSanPhamService;
import com.example.demo.service.IGioHangChiTietService;
import com.example.demo.service.IGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class CartController {

    @Autowired
    private IGioHangService gioHangService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IChiTietSanPhamService sanPhamService;

    @Autowired
    private IGioHangChiTietService gioHangChiTietService;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;


    public void thongTinCart(Principal principal, Model model) {
        Account account = accountService.findAccountByUsername(principal.getName());
        GioHang gioHang = gioHangService.findGioHangsByAccount(account);
        List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietService.findGioHangChiTietByGioHang(gioHang);
        float tongTien = 0;
        Integer soLuongHang = 0;
        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
            tongTien += Float.valueOf(gioHangChiTiet.getDonGia().floatValue() * gioHangChiTiet.getSoLuong());
            soLuongHang += gioHangChiTiet.getSoLuong();
        }
        model.addAttribute("soLuongHang", soLuongHang);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("danhSachGH", gioHangChiTietList);
    }

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        if (principal != null) {
            thongTinCart(principal, model);
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);
            model.addAttribute("thongBao", null);
        } else {
            Map<ChiTietSP, Integer> danhSachSPGH = new HashMap<>();
            Map<String, Integer> getSanPhamGH = gioHangService.getSanPhamTrongGio();
            for (Map.Entry<String, Integer> item : getSanPhamGH.entrySet()) {
                ChiTietSP chiTietSP = chiTietSanPhamService.getOne(item.getKey());
                Integer value = item.getValue();
                danhSachSPGH.put(chiTietSP, value);
            }
            float tongTienHang = 0;
            for (Map.Entry<ChiTietSP, Integer> item : danhSachSPGH.entrySet()) {
                tongTienHang = item.getKey().getGiaBan() * item.getValue();
            }
            model.addAttribute("cartUser", danhSachSPGH);
            model.addAttribute("tongTienHang", tongTienHang);
        }
        return "view/cart";
    }

    @GetMapping("/add-to-cart/{idCTSP}")
    public String addToCart(Principal principal, @PathVariable(value = "idCTSP") String idCTSP) {
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            GioHang gioHang = gioHangService.findGioHangsByAccount(account);
            ChiTietSP chiTietSP = sanPhamService.getOne(idCTSP);
            if (gioHang == null) {
                GioHang gioHangNew = new GioHang();
                gioHangNew.setAccount(account);
                gioHangNew.setMa(String.valueOf((Math.random() * 899999999) + 100000000));
                gioHangNew.setTinhTrang(0);
                gioHangNew.setNgayTao(new Date(new java.util.Date().getTime()));
                gioHangService.saveOrUpdate(gioHangNew);
            } else {
                GioHangChiTiet gioHangChiTiet = gioHangChiTietService.findGioHangChiTietByChiTietSPAndAndGioHang(chiTietSP, gioHang);
                if (gioHangChiTiet != null) {
                    gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() + 1);
                    gioHangChiTietService.saveOrUpdate(gioHangChiTiet);
                } else {
                    GioHangChiTiet gioHangChiTietNew = new GioHangChiTiet();
                    gioHangChiTietNew.setChiTietSP(chiTietSP);
                    gioHangChiTietNew.setGioHang(gioHang);
                    gioHangChiTietNew.setSoLuong(1);
                    gioHangChiTietNew.setDonGia(BigDecimal.valueOf(chiTietSP.getGiaBan()));
                    gioHangChiTietService.saveOrUpdate(gioHangChiTietNew);
                }

            }
        } else {
            gioHangService.addCartUser(idCTSP);
        }
        return "redirect:/user/home";
    }

    @GetMapping("/by-now/{id}")
    public String byNow(Principal principal, @PathVariable(value = "id") String idCTSP) {
        if (principal != null) {
            addToCart(principal, idCTSP);
        } else {
            gioHangService.addCartUser(idCTSP);
        }

        return "redirect:/user/cart";
    }

    @GetMapping("/cart-update-user/{id}")
    public String updateCartUser(@PathVariable(value = "id") String id, @RequestParam(value = "soLuongsss", required = false) Integer quantityUser) {
        System.out.println("Â" + quantityUser);
        Map<String, Integer> getGH = gioHangService.getSanPhamTrongGio();
        getGH.put(id, 5);
        return "redirect:/user/cart";

    }

    @GetMapping("/cart-delete-user/{id}")
    public String delete(@PathVariable(value = "id") String id) {
        Map<String, Integer> getGH = gioHangService.getSanPhamTrongGio();
        getGH.remove(id);
        return "redirect:/user/cart";

    }

}
