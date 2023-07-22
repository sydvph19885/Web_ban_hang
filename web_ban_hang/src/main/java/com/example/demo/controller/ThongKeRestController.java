package com.example.demo.controller;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.model.Top10BanChay;
import com.example.demo.service.IChiTietSanPhamService;
import com.example.demo.service.IHoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class ThongKeRestController {

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    public ThongKeRestController(IHoaDonChiTietService hoaDonChiTietService) {
        this.hoaDonChiTietService = hoaDonChiTietService;
    }

    @GetMapping("/top-10")
    public ResponseEntity<?> thongKe() {
        List<Top10BanChay> top10BanChays = hoaDonChiTietService.top10BanChay();
        return ResponseEntity.ok(top10BanChays);
    }

    @GetMapping("/tong-tien-aday")
    public ResponseEntity<?> tongTien() {
        List<Float> danhSanhHoaDonADay = hoaDonChiTietService.tongTienADay();
        float tongTienADay = 0;
        for (Float banChay : danhSanhHoaDonADay) {
            tongTienADay += banChay;
        }
        return ResponseEntity.ok(tongTienADay);
    }

    @GetMapping("/top-stock-in")
    public ResponseEntity<?> topStockIn() {
        List<ChiTietSP> top10StockIn = chiTietSanPhamService.top10StockIn();
        return ResponseEntity.ok(top10StockIn);
    }
}
