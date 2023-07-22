package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.HoaDon;

import java.util.List;

public interface IHoaDonService {
    List<HoaDon> findHoaDonsByAccount(Account account);

    void saveOrUpdate(HoaDon hoaDon);

    HoaDon getOne(String id);
}
