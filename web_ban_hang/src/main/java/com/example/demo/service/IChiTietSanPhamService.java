package com.example.demo.service;

import com.example.demo.entity.ChiTietSP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IChiTietSanPhamService {
    Page<ChiTietSP> getAll(Pageable pageable);

    void saveOrUpdate(ChiTietSP chiTietSP);

    void delete(String id);

    ChiTietSP getOne(String id);

    List<ChiTietSP> top10StockIn();

    Page<ChiTietSP> findChiTietSPByTenSanPhamContains(String ten,Pageable pageable);
}
