package com.example.demo.controller;

import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.service.IGioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class CartRestController {

    @Autowired
    private IGioHangChiTietService gioHangChiTietService;

    @DeleteMapping("/cart/delete/{id}")
    public ResponseEntity<?> deleteGioHang(@PathVariable(value = "id") String id) {
        gioHangChiTietService.delete(id);
        return ResponseEntity.ok("OKElA");
    }

    @GetMapping("/cart-detail/{id}")
    public ResponseEntity<GioHangChiTiet> findById(@PathVariable(value = "id") String id) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietService.findGioHangChiTietById(id);
        if (gioHangChiTiet != null) {
            return ResponseEntity.ok(gioHangChiTiet);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/cart/update")
    public ResponseEntity<?> update(@RequestBody GioHangChiTiet gioHangChiTiet) {
        gioHangChiTietService.saveOrUpdate(gioHangChiTiet);
        return ResponseEntity.ok("OKE");
    }
}
