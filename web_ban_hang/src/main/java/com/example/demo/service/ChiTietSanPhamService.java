package com.example.demo.service;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.NhaSanXuat;
import com.example.demo.repositoty.IChiTietSanPhamReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChiTietSanPhamService implements IChiTietSanPhamService {

    @Autowired
    private IChiTietSanPhamReponsitory sanPhamReponsitory;

    public List<ChiTietSP> top10StockInMana = new ArrayList<>();

    @Override
    public Page<ChiTietSP> getAll(Pageable pageable) {
        return sanPhamReponsitory.findAll(pageable);
    }

    @Override
    public void saveOrUpdate(ChiTietSP chiTietSP) {
        sanPhamReponsitory.saveAndFlush(chiTietSP);
    }

    @Override
    public void delete(String id) {
        sanPhamReponsitory.deleteById(id);
    }

    @Override
    public ChiTietSP getOne(String id) {
        return sanPhamReponsitory.findById(id).get();
    }

    @Override
    public List<ChiTietSP> top10StockIn() {
        Pageable pageable = PageRequest.of(0, 10);
        top10StockInMana = sanPhamReponsitory.top10StockIn(pageable);
        return top10StockInMana;
    }

    @Override
    public Page<ChiTietSP> findChiTietSPByTenSanPhamContains(String ten, Pageable pageable) {
        return sanPhamReponsitory.findChiTietSPByTenSanPhamContains(ten, pageable);
    }

    @Override
    public Page<ChiTietSP> searchDaThuocTinh(
            Integer namBH,
            MauSac mauSac,
            NhaSanXuat nhaSanXuat,
            Integer voucher,
            Integer size,
            Pageable pageable) {
        return sanPhamReponsitory.searchDaThuocTinh(namBH,mauSac,nhaSanXuat,voucher,size,pageable);
    }

}
