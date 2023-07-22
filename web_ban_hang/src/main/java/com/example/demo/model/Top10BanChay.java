package com.example.demo.model;

import com.example.demo.entity.ChiTietSP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Top10BanChay {
    private ChiTietSP chiTietSP;
    private Long soLuongBan;

}
