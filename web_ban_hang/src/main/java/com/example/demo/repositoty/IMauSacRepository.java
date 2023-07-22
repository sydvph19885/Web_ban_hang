package com.example.demo.repositoty;

import com.example.demo.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMauSacRepository extends JpaRepository<MauSac, String> {
}
