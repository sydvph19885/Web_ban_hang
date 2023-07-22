package com.example.demo.model;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.NhaSanXuat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailPeoduct {

    private ChiTietSP chiTietSP;
    private List<MauSac> mauSacList;
    private List<NhaSanXuat> nhaSanXuatList;
}
