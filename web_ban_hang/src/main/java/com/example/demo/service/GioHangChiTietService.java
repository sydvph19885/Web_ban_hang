package com.example.demo.service;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.repositoty.IGioHangChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GioHangChiTietService implements IGioHangChiTietService {

    @Autowired
    private IGioHangChiTietRepository gioHangChiTietRepository;

    @Override
    public List<GioHangChiTiet> findGioHangChiTietByGioHang(GioHang gioHang) {
        return gioHangChiTietRepository.findGioHangChiTietByGioHang(gioHang);
    }

    @Override
    public void saveOrUpdate(GioHangChiTiet gioHangChiTiet) {
        gioHangChiTietRepository.saveAndFlush(gioHangChiTiet);
    }

    @Override
    public GioHangChiTiet findGioHangChiTietByChiTietSPAndAndGioHang(ChiTietSP chiTietSP, GioHang gioHang) {
        return gioHangChiTietRepository.findGioHangChiTietByChiTietSPAndAndGioHang(chiTietSP, gioHang);
    }

    @Override
    public void delete(String id) {
        gioHangChiTietRepository.deleteById(id);
    }

    @Override
    public GioHangChiTiet findGioHangChiTietById(String id) {
        return gioHangChiTietRepository.findGioHangChiTietById(id);
    }
}
