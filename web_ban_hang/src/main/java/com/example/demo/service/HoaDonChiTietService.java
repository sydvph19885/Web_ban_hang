package com.example.demo.service;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.model.Top10BanChay;
import com.example.demo.repositoty.IHoaDonChiTietRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HoaDonChiTietService implements IHoaDonChiTietService {

    @Autowired
    private IHoaDonChiTietRepositoty hoaDonChiTietRepositoty;

    @Override
    public List<HoaDonChiTiet> findByHoaDon(HoaDon hoaDon) {
        return hoaDonChiTietRepositoty.findByHoaDon(hoaDon);
    }

    @Override
    public void saveOrUpdate(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepositoty.saveAndFlush(hoaDonChiTiet);
    }

    @Override
    public List<Top10BanChay> top10BanChay() {
        Pageable pageable = PageRequest.of(0, 10);
        return hoaDonChiTietRepositoty.top10BanChay(pageable);
    }

    @Override
    public List<Float> tongTienADay() {
        return hoaDonChiTietRepositoty.tongTienADay();
    }
}
