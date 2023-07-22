package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.HoaDon;
import com.example.demo.repositoty.IHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HoaDonService implements IHoaDonService {

    @Autowired
    private IHoaDonRepository hoaDonRepository;

    @Override
    public List<HoaDon> findHoaDonsByAccount(Account account) {
        return hoaDonRepository.findHoaDonsByAccount(account);
    }

    @Override
    public void saveOrUpdate(HoaDon hoaDon) {
        hoaDonRepository.saveAndFlush(hoaDon);
    }

    @Override
    public HoaDon getOne(String id) {
        return hoaDonRepository.findById(id).get();
    }
}
