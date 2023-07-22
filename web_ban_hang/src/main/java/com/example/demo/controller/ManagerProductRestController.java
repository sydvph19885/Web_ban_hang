package com.example.demo.controller;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.NhaSanXuat;
import com.example.demo.model.DetailPeoduct;
import com.example.demo.service.IChiTietSanPhamService;
import com.example.demo.service.IMauSacService;
import com.example.demo.service.INhaSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ManagerProductRestController {

    @Autowired
    IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private IMauSacService mauSacService;

    @Autowired
    private INhaSanXuatService nhaSanXuatService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<ChiTietSP>> delete(@PathVariable(value = "id") String id) {
        if (chiTietSanPhamService.getOne(id) != null) {
            chiTietSanPhamService.delete(id);
            return ResponseEntity.ok(chiTietSanPhamService.getAll(PageRequest.of(0, 4)).getContent());
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DetailPeoduct> detail(@PathVariable(value = "id") String id) {
        ChiTietSP chiTietSP = chiTietSanPhamService.getOne(id);
        List<MauSac> mauSacList = mauSacService.getAll();
        List<NhaSanXuat> nhaSanXuatList = nhaSanXuatService.getAll();
        DetailPeoduct detailPeoduct = new DetailPeoduct();
        detailPeoduct.setChiTietSP(chiTietSP);
        detailPeoduct.setMauSacList(mauSacList);
        detailPeoduct.setNhaSanXuatList(nhaSanXuatList);
        if (chiTietSP != null) {
            return ResponseEntity.ok(detailPeoduct);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
