package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@Table(name = "gio_hang")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GioHang {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "tinh_trang")
    private int tinhTrang;
    @JoinColumn(name = "idkh")
    @ManyToOne(cascade = CascadeType.DETACH)
    private Account account;

    public GioHang(Account account) {
        this.account = account;
    }


    public GioHang(String ma, Date ngayTao, Account account) {
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.account = account;
    }
}
