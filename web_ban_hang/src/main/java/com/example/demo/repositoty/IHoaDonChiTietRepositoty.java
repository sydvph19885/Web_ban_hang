package com.example.demo.repositoty;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.model.Top10BanChay;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IHoaDonChiTietRepositoty extends JpaRepository<HoaDonChiTiet, String> {
    List<HoaDonChiTiet> findByHoaDon(HoaDon hoaDon);

    @Query("""
                    SELECT new com.example.demo.model.Top10BanChay(ct.chiTietSP,SUM(ct.soLuong))  FROM HoaDonChiTiet ct
                    WHERE ct.hoaDon IN (SELECT id FROM HoaDon WHERE tinhTrang = 1)
                    GROUP BY ct.chiTietSP
                    ORDER BY SUM(ct.soLuong) DESC 
            """)
    List<Top10BanChay> top10BanChay(Pageable pageable);

    //    SELECT new com.example.demo.model.Top10BanChay(ct.chiTietSP,SUM(ct.donGia * ct.soLuong)) FROM HoaDonChiTiet ct
//    WHERE ct.hoaDon IN (SELECT id FROM HoaDon WHERE ngayTao =  CAST(GETDATE() AS DATE) )
//    GROUP BY ct.hoaDon
    @Query(value = """
            SELECT SUM(ct.don_gia * ct.so_luong) FROM hoa_don_chi_tiet ct
            WHERE ct.id_hoa_don IN (SELECT id FROM hoa_don WHERE ngay_tao =  CAST(GETDATE() AS DATE) )
            GROUP BY ct.id_hoa_don
             """, nativeQuery = true)
    List<Float> tongTienADay();

}
