package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
public class CheckOutController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IGioHangService gioHangService;

    @Autowired
    private IGioHangChiTietService gioHangChiTietService;

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;


    @GetMapping("/check-out-user")
    public String checkOut() {
        return "redirect:/user/cart";
    }

    @GetMapping("/check-out")
    public String createHoaDon(Model model,
                               Principal principal,
                               @RequestParam(name = "tenNguoiNhan") String tenNguoiNhan,
                               @RequestParam(name = "diaChi") String diaChi,
                               @RequestParam(name = "sdt") String sdt
    ) {
        try {
            Account account = accountService.findAccountByUsername(principal.getName());
            GioHang gioHang = gioHangService.findGioHangsByAccount(account);
            List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietService.findGioHangChiTietByGioHang(gioHang);
            HoaDon hoaDon = new HoaDon();
            hoaDon.setAccount(account);
            hoaDon.setNgayTao(new Date(new java.util.Date().getTime()));
            hoaDon.setTinhTrang(0);
            hoaDon.setTenNguoiNhan(tenNguoiNhan);
            hoaDon.setSdt(sdt);
            hoaDon.setDiaChi(diaChi);
            hoaDon.setMa(String.valueOf((int) (Math.random() * 899999999) + 100000000));
            hoaDonService.saveOrUpdate(hoaDon);
            for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setHoaDon(hoaDon);
                hoaDonChiTiet.setChiTietSP(gioHangChiTiet.getChiTietSP());
                hoaDonChiTiet.setSoLuong(gioHangChiTiet.getSoLuong());
                hoaDonChiTiet.setDonGia(gioHangChiTiet.getDonGia());
                hoaDonChiTietService.saveOrUpdate(hoaDonChiTiet);
                gioHangChiTietService.delete(gioHangChiTiet.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/danh-sach-hoa-don";
    }

    @GetMapping("/danh-sach-hoa-don")
    public String viewHoaDonAccount(Principal principal, Model model) {
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            List<HoaDon> hoaDonByAccount = hoaDonService.findHoaDonsByAccount(account);
            model.addAttribute("listHoaDon", hoaDonByAccount);
            model.addAttribute("account", account);
        }

        return "view/hoa-don";
    }

    @GetMapping("/hoa-don-chi-tiet/{id}")
    public String hoaDonChiTiet(Model model, @PathVariable(value = "id") String idHoaDon, Principal principal) {
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            HoaDon hoaDon = hoaDonService.getOne(idHoaDon);
            List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.findByHoaDon(hoaDon);
            float tongTienSP = 0;
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietList) {
                tongTienSP += hoaDonChiTiet.getSoLuong() * hoaDonChiTiet.getDonGia().doubleValue();
            }
            model.addAttribute("tongTienSP", tongTienSP);
            model.addAttribute("hdct", hoaDonChiTietList);
            model.addAttribute("hoaDon", hoaDon);
            model.addAttribute("account", account);
            model.addAttribute("time", new Date(new java.util.Date().getTime()));
        }

        return "view/hoa-don-chi-tiet";
    }

    @GetMapping("/thanh-toan/{id}")
    public String thanhToan(@PathVariable(value = "id") String idHD) {
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        hoaDon.setTinhTrang(1);
        hoaDonService.saveOrUpdate(hoaDon);
        return "redirect:/danh-sach-hoa-don";
    }
}
