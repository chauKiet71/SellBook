package com.example.booker.dao;

import com.example.booker.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DanhGiaDao extends JpaRepository<DanhGia, Integer> {

    @Query("SELECT d FROM DanhGia d WHERE d.san_pham.ma_san_pham = :maSanPham ORDER BY d.ma_danh_gia desc")
    List<DanhGia> findByMaSanPham(@Param("maSanPham") Integer maSanPham);

    @Query("SELECT COUNT(d) FROM DanhGia d WHERE d.san_pham.ma_san_pham = :maSanPham")
    long countByMaSanPham(@Param("maSanPham") int maSanPham);

    @Query("SELECT d FROM DanhGia d WHERE d.san_pham.ma_san_pham = :maSanPham AND d.diem_danh_gia = :diemDanhGia")
    List<DanhGia> findByMaSanPhamAndDiemDanhGia(@Param("maSanPham") int maSanPham, @Param("diemDanhGia") int diemDanhGia);

}
