package com.example.demo.repositoty;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGioHangChiTietRepository extends JpaRepository<GioHangChiTiet, String> {
    List<GioHangChiTiet> findGioHangChiTietByGioHang(GioHang gioHang);

    GioHangChiTiet findGioHangChiTietByChiTietSPAndAndGioHang(ChiTietSP chiTietSP, GioHang gioHang);

    GioHangChiTiet findGioHangChiTietById(String id);
}
