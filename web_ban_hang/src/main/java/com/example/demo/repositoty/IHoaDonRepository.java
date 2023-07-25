package com.example.demo.repositoty;

import com.example.demo.entity.Account;
import com.example.demo.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHoaDonRepository extends JpaRepository<HoaDon, String> {
    List<HoaDon> findHoaDonsByAccount(Account account);

    List<HoaDon> findHoaDonByTinhTrang(Integer tinhTrang);
}
