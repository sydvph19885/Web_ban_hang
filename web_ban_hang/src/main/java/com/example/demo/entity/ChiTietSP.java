package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "chi_tietsp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class ChiTietSP {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_nsx")
    private NhaSanXuat nhaSanXuat;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_mau_sac")
    private MauSac mauSac;
    @Column(name = "nambh")
    private int namBH;
    @Column(name = "mo_ta", columnDefinition = "NVARCHAR(MAX)")
    private String moTa;
    @Column(name = "Img", columnDefinition = "NVARCHAR(MAX)")
    private String image;
    @Column(name = "so_luong_ton")
    private int soLuongTon;
    @Column(name = "gia_nhap")
    private float giaNhap;
    @Column(name = "gia_ban")
    private float giaBan;
    @Column(name = "size")
    private int size;
    @Column(name = "ngay_nhap")
    private Date ngayNhap;
    @Column(name = "voucher")
    private int voucher;
    @Column(name = "tensanpham",columnDefinition = "NVARCHAR(MAX)")
    private String tenSanPham;




}
