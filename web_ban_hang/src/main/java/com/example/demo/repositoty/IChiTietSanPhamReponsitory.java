package com.example.demo.repositoty;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.NhaSanXuat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChiTietSanPhamReponsitory extends JpaRepository<ChiTietSP, String> {

    @Query("""
            SELECT ct FROM ChiTietSP ct
            ORDER BY ct.ngayNhap ASC
            """)
    List<ChiTietSP> top10StockIn(Pageable pageable);

    Page<ChiTietSP> findChiTietSPByTenSanPhamContains(String ten, Pageable pageable);

    @Query("""
              SELECT ct FROM ChiTietSP ct
              WHERE ct.namBH IS NULL OR ct.namBH = :namBH
              AND ct.mauSac IS NULL OR ct.mauSac = :mauSac
              AND ct.nhaSanXuat IS NULL OR ct.nhaSanXuat = :nsx
              AND ct.voucher IS NULL OR ct.voucher = :voucher
              AND ct.size IS NULL OR ct.size = :size
            """)
    Page<ChiTietSP> searchDaThuocTinh(
            @Param(value = "namBH") Integer namBH,
            @Param(value = "mauSac") MauSac mauSac,
            @Param(value = "nsx") NhaSanXuat nhaSanXuat,
            @Param(value = "voucher") Integer voucher,
            @Param(value = "size") Integer size,
            Pageable pageable
    );

}
