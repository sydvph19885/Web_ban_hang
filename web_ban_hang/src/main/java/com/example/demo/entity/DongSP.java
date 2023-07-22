package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "dongsp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DongSP {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "ma")
    private String ma;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String ten;

    public DongSP(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }
}
