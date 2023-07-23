package com.example.demo.controller;


import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.NhaSanXuat;
import com.example.demo.service.IChiTietSanPhamService;
import com.example.demo.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class MauSacRestController {

    @Autowired
    private IMauSacService mauSacService;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @PostMapping("/save-ms")
    public ResponseEntity<MauSac> save(@RequestBody MauSac mauSac) {
        mauSacService.saveOrUpdate(mauSac);
        return ResponseEntity.ok(mauSac);
    }

//    @GetMapping("/search-multiple")
//    public ResponseEntity<?> searchDaThuocTinh(
//            @RequestParam(value = "namBH", required = false) Integer namBH,
//            @RequestParam(value = "mauSac", required = false) MauSac mauSac,
//            @RequestParam(value = "nsx", required = false) NhaSanXuat nhaSanXuat,
//            @RequestParam(value = "voucher", required = false) Integer voucher,
//            @RequestParam(value = "size", required = false) Integer size
//    ) {
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<ChiTietSP> listSearch = chiTietSanPhamService.searchDaThuocTinh(namBH, mauSac, nhaSanXuat, voucher, size, pageable);
//        return ResponseEntity.ok(listSearch.getContent());
//    }
}
