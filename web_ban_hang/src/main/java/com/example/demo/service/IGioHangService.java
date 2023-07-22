package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.GioHang;

import java.util.Map;

public interface IGioHangService {
    GioHang findGioHangsByAccount(Account account);

    void saveOrUpdate(GioHang gioHang);

    Map<String, Integer> getSanPhamTrongGio();

    void addCartUser(String id);
}
