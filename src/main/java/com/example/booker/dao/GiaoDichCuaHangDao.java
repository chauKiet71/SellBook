package com.example.booker.dao;

import com.example.booker.entity.GiaoDichCuaHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiaoDichCuaHangDao extends JpaRepository<GiaoDichCuaHang, Integer> {

    @Query("SELECT gd FROM GiaoDichCuaHang gd WHERE gd.trang_thai = :tt")
    List<GiaoDichCuaHang> getGiaoDichCuaHangByTrang_thai(int tt);
}
