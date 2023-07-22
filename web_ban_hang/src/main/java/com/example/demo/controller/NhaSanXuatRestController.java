package com.example.demo.controller;

import com.example.demo.entity.NhaSanXuat;
import com.example.demo.service.INhaSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class NhaSanXuatRestController {

    @Autowired
    private INhaSanXuatService nhaSanXuatService;

    @PostMapping("/save-nsx")
    public ResponseEntity<NhaSanXuat> save(@RequestBody NhaSanXuat nhaSanXuat) {
        nhaSanXuatService.saveOrUpdate(nhaSanXuat);
        return ResponseEntity.ok(nhaSanXuat);
    }
}
