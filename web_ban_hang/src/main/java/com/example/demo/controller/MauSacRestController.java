package com.example.demo.controller;


import com.example.demo.entity.MauSac;
import com.example.demo.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class MauSacRestController {

    @Autowired
    private IMauSacService mauSacService;

    @PostMapping("/save-ms")
    public ResponseEntity<MauSac> save(@RequestBody MauSac mauSac) {
        mauSacService.saveOrUpdate(mauSac);
        return ResponseEntity.ok(mauSac);
    }
}
