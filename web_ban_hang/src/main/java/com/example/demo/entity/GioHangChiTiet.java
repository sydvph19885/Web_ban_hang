package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Table(name = "gio_hang_chi_tiet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GioHangChiTiet {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "don_gia")
    private BigDecimal donGia;
    @Column(name = "don_gia_khi_giam")
    private BigDecimal donGiaKhiGiam;
    @Column(name = "so_luong")
    private int soLuong;
    @JoinColumn(name = "id_chi_tietsp")
    @ManyToOne(cascade = CascadeType.DETACH)
    private ChiTietSP chiTietSP;
    @JoinColumn(name = "id_gio_hang")
    @ManyToOne(cascade = CascadeType.DETACH)
    private GioHang gioHang;

    public GioHangChiTiet(BigDecimal donGia, BigDecimal donGiaKhiGiam, int soLuong, ChiTietSP chiTietSP, GioHang gioHang) {
        this.donGia = donGia;
        this.donGiaKhiGiam = donGiaKhiGiam;
        this.soLuong = soLuong;
        this.chiTietSP = chiTietSP;
        this.gioHang = gioHang;
    }
}
