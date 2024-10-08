package com.example.booker.service.nguoidung;

import com.example.booker.entity.SanPham;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface SanPhamService {

    List<SanPham> findAll(int ma_san_pham);

    SanPham create(int ma_cua_hang, SanPham sanPham);

    List<SanPham> findByTenSanPham(int ma_san_pham, String tenSanPham);

    List<SanPham> findByTheLoai(int ma_cua_hang, int id);

    List<SanPham> findByCreateDate(int ma_cua_hang, LocalDate createDate);

    SanPham findById(int id);

    void deleteById(int id);

    SanPham update(SanPham sanPham);

    List<SanPham> sortPriceAsc(int ma_cua_hang);

    List<SanPham> sortPriceDesc(int ma_cua_hang);
}
