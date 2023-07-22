package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.GioHang;
import com.example.demo.repositoty.IGioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GioHangService implements IGioHangService {

    @Autowired
    private IGioHangRepository gioHangRepository;

    private Map<String, Integer> sanPhamTrongGio = new HashMap<>();

    @Override
    public GioHang findGioHangsByAccount(Account account) {
        return gioHangRepository.findGioHangsByAccount(account);
    }

    @Override
    public void saveOrUpdate(GioHang gioHang) {
        gioHangRepository.saveAndFlush(gioHang);
    }

    @Override
    public Map<String, Integer> getSanPhamTrongGio() {
        return sanPhamTrongGio;
    }

    @Override
    public void addCartUser(String id) {
        if (getSanPhamTrongGio().containsKey(id)) {
            Integer soLuong = getSanPhamTrongGio().get(id);
            sanPhamTrongGio.put(id, soLuong + 1);
        } else {
            sanPhamTrongGio.put(id, 1);
        }
    }
}
