package com.example.demo.repositoty;

import com.example.demo.entity.ChiTietSP;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChiTietSanPhamReponsitory extends JpaRepository<ChiTietSP, String> {

    @Query("""
            SELECT ct FROM ChiTietSP ct
            ORDER BY ct.ngayNhap ASC
            """)
    List<ChiTietSP> top10StockIn(Pageable pageable);

}
