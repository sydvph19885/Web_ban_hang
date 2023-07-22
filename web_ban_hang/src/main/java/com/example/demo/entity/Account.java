package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Account {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    private String email;
    @Column(name = "ho_ten", columnDefinition = "NVARCHAR(50)")
    private String hoTen;
    private String username;
    @Column(name = "ma")
    private String ma;
    @Column(name = "image", columnDefinition = "NVARCHAR(MAX)")
    private String image;
    @Column(name = "mat_khau")
    private String matKhau;
    @Column(name = "role")
    private String role;
    @Column(name = "sdt")
    private String sdt;
    @Column(name = "code_mail")
    private String code_mail;

    public Account(String email, String hoTen, String ma, String image, String matKhau, String role) {
        this.email = email;
        this.hoTen = hoTen;
        this.ma = ma;
        this.image = image;
        this.matKhau = matKhau;
        this.role = role;
    }

    public Account(String email, String hoTen, String sdt) {
        this.email = email;
        this.hoTen = hoTen;
        this.sdt = sdt;
    }
}
