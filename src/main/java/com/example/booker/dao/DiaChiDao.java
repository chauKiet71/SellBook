package com.example.booker.dao;

import com.example.booker.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaChiDao extends JpaRepository<DiaChi, Integer> {
    @Query("select dc from DiaChi dc " +
            "where dc.tai_khoan.id_tai_khoan = :ma_tk")
    List<DiaChi> getListDiaChi(int ma_tk);
}
