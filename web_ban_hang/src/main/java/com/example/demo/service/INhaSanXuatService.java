package com.example.demo.service;


import com.example.demo.entity.NhaSanXuat;

import java.util.List;

public interface INhaSanXuatService {
    List<NhaSanXuat> getAll();

    NhaSanXuat getOne(String id);

    void saveOrUpdate(NhaSanXuat nhaSanXuat);
}
