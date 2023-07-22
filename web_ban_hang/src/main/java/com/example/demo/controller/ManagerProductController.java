package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.NhaSanXuat;
import com.example.demo.service.IAccountService;
import com.example.demo.service.IChiTietSanPhamService;
import com.example.demo.service.IMauSacService;
import com.example.demo.service.INhaSanXuatService;
import com.example.demo.utili.UploadImg;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Date;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@MultipartConfig
public class ManagerProductController {

    @Autowired
    IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private IMauSacService mauSacService;

    @Autowired
    private INhaSanXuatService nhaSanXuatService;

    @Autowired
    UploadImg uploadImg;

    @Autowired
    private IAccountService accountService;

    @GetMapping("/manager")
    public String viewManager(Principal principal, Model model, @RequestParam(value = "size", required = false) Optional<Integer> size, @RequestParam(value = "page", required = false) Optional<Integer> trangSo) {
        Pageable pageable = PageRequest.of(trangSo.orElse(0), size.orElse(5));
        Page<ChiTietSP> chiTietSPPage = chiTietSanPhamService.getAll(pageable);
        model.addAttribute("listSanPham", chiTietSPPage.getContent());
        model.addAttribute("mauSacLisst", mauSacService.getAll());
        model.addAttribute("nsxLisst", nhaSanXuatService.getAll());
        model.addAttribute("trangHT", chiTietSPPage.getNumber());
        model.addAttribute("tongTrang", chiTietSPPage.getTotalPages());
        if (principal != null) {
            Account account = accountService.findAccountByUsername(principal.getName());
            model.addAttribute("account", account);

        } else {
            model.addAttribute("nullAccount", principal);
        }
        return "view/manager";
    }

    @PostMapping("/update")
    public String update(@RequestParam(value = "idCTSP") String id,
                         @RequestParam(value = "tenSanPham") String tenSanPham,
                         @RequestParam(value = "selectNSX") String idNSX,
                         @RequestParam(value = "selectMS") String idMS,
                         @RequestParam(value = "size") Integer size,
                         @RequestParam(value = "namBH") Integer namBH,
                         @RequestParam(value = "soLuong") Integer soLuong,
                         @RequestParam(value = "giaNhap") Float giaNhap,
                         @RequestParam(value = "giaBan") Float giaBan,
                         @RequestParam(value = "voucher") Integer voucher,
                         @RequestParam(value = "moTa") String moTa,
                         @RequestParam(value = "image", required = false) MultipartFile file,
                         Model model,
                         Principal principal) {
        uploadImg.uploadImg(file);
        NhaSanXuat nhaSanXuat = nhaSanXuatService.getOne(idNSX);
        MauSac mauSac = mauSacService.getOne(idMS);
        ChiTietSP chiTietSP = new ChiTietSP();
        chiTietSP.setId(id);
        chiTietSP.setTenSanPham(tenSanPham);
        chiTietSP.setNhaSanXuat(nhaSanXuat);
        chiTietSP.setMauSac(mauSac);
        chiTietSP.setSize(size);
        chiTietSP.setNamBH(namBH);
        chiTietSP.setSoLuongTon(soLuong);
        chiTietSP.setGiaNhap(giaNhap);
        chiTietSP.setGiaBan(giaBan);
        chiTietSP.setVoucher(voucher);
        chiTietSP.setMoTa(moTa);
        chiTietSP.setNgayNhap(new Date(new java.util.Date().getTime()));
        chiTietSP.setImage(file.getOriginalFilename());
        chiTietSanPhamService.saveOrUpdate(chiTietSP);
        viewManager(principal, model, Optional.of(5), Optional.of(0));
        model.addAttribute("thongBao", "Cập nhật thành công!");
        return "view/manager";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam(value = "tenSanPham") String tenSanPham,
            @RequestParam(value = "selectNSX") String idNSX,
            @RequestParam(value = "selectMS") String idMS,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "namBH") Integer namBH,
            @RequestParam(value = "soLuong") Integer soLuong,
            @RequestParam(value = "giaNhap") Float giaNhap,
            @RequestParam(value = "giaBan") Float giaBan,
            @RequestParam(value = "voucher") Integer voucher,
            @RequestParam(value = "moTa") String moTa,
            @RequestParam(value = "image", required = false) MultipartFile file,
            Model model,
            Principal principal) {
        uploadImg.uploadImg(file);
        NhaSanXuat nhaSanXuat = nhaSanXuatService.getOne(idNSX);
        MauSac mauSac = mauSacService.getOne(idMS);
        ChiTietSP chiTietSP = new ChiTietSP();
        chiTietSP.setTenSanPham(tenSanPham);
        chiTietSP.setNhaSanXuat(nhaSanXuat);
        chiTietSP.setMauSac(mauSac);
        chiTietSP.setSize(size);
        chiTietSP.setNamBH(namBH);
        chiTietSP.setSoLuongTon(soLuong);
        chiTietSP.setGiaNhap(giaNhap);
        chiTietSP.setGiaBan(giaBan);
        chiTietSP.setVoucher(voucher);
        chiTietSP.setMoTa(moTa);
        chiTietSP.setNgayNhap(new Date(new java.util.Date().getTime()));
        chiTietSP.setImage(file.getOriginalFilename());
        chiTietSanPhamService.saveOrUpdate(chiTietSP);
        viewManager(principal, model, Optional.of(5), Optional.of(0));
        model.addAttribute("thongBao", "Thêm thành công!");
        return "view/manager";
    }
}
