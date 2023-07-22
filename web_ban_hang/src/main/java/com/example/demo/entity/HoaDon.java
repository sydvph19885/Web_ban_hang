package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@Table(name = "hoa_don")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDon {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;
    @Column(name = "ngay_ship")
    private Date ngayShip;
    @Column(name = "sdt")
    private String sdt;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "ten_nguoi_nhan",columnDefinition = "NVARCHAR(MAX)")
    private String tenNguoiNhan;
    @Column(name = "tinh_trang")
    private int tinhTrang;
    @JoinColumn(name = "idkh")
    @ManyToOne(cascade = CascadeType.DETACH)
    private Account account;

    public HoaDon(String ma, Date ngayTao, String sdt, String diaChi, String tenNguoiNhan, int tinhTrang, Account account) {
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.tenNguoiNhan = tenNguoiNhan;
        this.tinhTrang = tinhTrang;
        this.account = account;
    }
}
