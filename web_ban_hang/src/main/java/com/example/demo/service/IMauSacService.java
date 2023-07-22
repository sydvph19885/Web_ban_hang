package com.example.demo.service;

import com.example.demo.entity.MauSac;

import java.util.List;

public interface IMauSacService {
    List<MauSac> getAll();

    MauSac getOne(String id);

    void saveOrUpdate(MauSac mauSac);
}
