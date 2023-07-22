package com.example.demo.service;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.model.Top10BanChay;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface IHoaDonChiTietService {
    List<HoaDonChiTiet> findByHoaDon(HoaDon hoaDon);

    void saveOrUpdate(HoaDonChiTiet hoaDonChiTiet);

    List<Top10BanChay> top10BanChay();

    List<Float> tongTienADay();
}
