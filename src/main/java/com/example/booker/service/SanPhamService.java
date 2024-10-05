package com.example.booker.service;

import com.example.booker.entity.SanPham;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SanPhamService {

    List<SanPham> findAll();
    SanPham create(SanPham sanPham);
    List<SanPham> findByTenSanPham(String tenSanPham);
    List<SanPham> findByTheLoai(int id);
    List<SanPham> findByCreateDate(LocalDate createDate);
    SanPham findById(int id);
    void deleteById(int id);
    SanPham update(SanPham sanPham);
}
