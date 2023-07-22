package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "mau_sac")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MauSac {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    private String ma;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String ten;

    public MauSac(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }
}
