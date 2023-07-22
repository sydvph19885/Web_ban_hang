package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Table(name = "hoa_don_chi_tiet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "don_gia")
    private BigDecimal donGia;
    @Column(name = "so_luong")
    private int soLuong;
    @JoinColumn(name = "id_chi_tietsp")
    @ManyToOne(cascade = CascadeType.DETACH)
    private ChiTietSP chiTietSP;
    @JoinColumn(name = "id_hoa_don")
    @ManyToOne(cascade = CascadeType.DETACH)
    private HoaDon hoaDon;

    public HoaDonChiTiet(BigDecimal donGia, int soLuong, ChiTietSP chiTietSP, HoaDon hoaDon) {
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.chiTietSP = chiTietSP;
        this.hoaDon = hoaDon;
    }
}
