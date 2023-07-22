package com.example.demo.service;

import com.example.demo.entity.MauSac;
import com.example.demo.repositoty.IMauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacService implements IMauSacService {

    @Autowired
    private IMauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac getOne(String id) {
        return mauSacRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate(MauSac mauSac) {
        mauSacRepository.saveAndFlush(mauSac);
    }
}
