package com.example.booker.service.nguoidung.impl;



import com.example.booker.dao.SanPhamDao;
import com.example.booker.dao.SanPhamViewDao;
import com.example.booker.entity.SanPham;
import com.example.booker.entity.view.SanPhamView;
import com.example.booker.service.nguoidung.SanPhamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamDao sanPhamDao;

    @Autowired
    SanPhamViewDao sanPhamViewDao;


    @Override
    public List<SanPhamView> findAll(int ma_san_pham) {
        return sanPhamViewDao.findAllSanPham(ma_san_pham);
    }

    @Override
    public SanPham create(int ma_cua_hang, SanPham sanPham) {
        //kiem tra san pham ton tai
        if (sanPhamDao.existBySanPham(ma_cua_hang, sanPham.getTen_san_pham()))
            throw new RuntimeException("Product exist");
        return sanPhamDao.save(sanPham);
    }

    @Override
    public List<SanPhamView> findByTenSanPham(int ma_san_pham, String tenSanPham) {
        return sanPhamViewDao.findByTenSanPhamContainingIgnoreCase(ma_san_pham, tenSanPham);
    }

    @Override
    public List<SanPhamView> findByTheLoai(int ma_cua_hang, int id) {
        return sanPhamViewDao.findSanPhamByTheLoai(ma_cua_hang, id);
    }

    @Override
    public List<SanPhamView> findByCreateDate(int ma_cua_hang, LocalDate createDate) {
        return sanPhamViewDao.findSanPhamByCreateDate(ma_cua_hang, createDate);
    }

    @Override
    public SanPham findById(int id) {
        return sanPhamDao.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        try {
            sanPhamDao.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
    }

    @Override
    public SanPham update(SanPham sanPham) {
        return sanPhamDao.save(sanPham);
    }

    @Override
    public List<SanPham> sortPriceAsc(int ma_cua_hang) {
        return sanPhamDao.findAllSanPhamSortPriceAsc(ma_cua_hang);
    }

    @Override
    public List<SanPham> sortPriceDesc(int ma_cua_hang) {
        return sanPhamDao.findAllSanPhamSortPriceDesc(ma_cua_hang);
    }

    @Override
    public List<SanPhamView> sanPhamByTrangThaiKhoa(int ma_cua_hang) {
        return sanPhamViewDao.findAllSanPhamBiKhoa(ma_cua_hang);
    }

    @Override
    public List<SanPhamView> sanPhamByChoDuyet(int ma_cua_hang) {
        return sanPhamViewDao.findAllSanPhamChoDuyet(ma_cua_hang);
    }

    @Override
    public List<SanPhamView> sanPhamByHetHang(int ma_cua_hang) {
        return sanPhamViewDao.findAllSanPhamHetHang(ma_cua_hang);
    }

    @Override
    public List<SanPhamView> sanPhamByConHang(int ma_cua_hang) {
        return sanPhamViewDao.findAllSanPhamConHang(ma_cua_hang);
    }

    @Override
    public List<SanPhamView> searchSanPhamByTrangThai(int ma_cua_hang, int matt) {
        return sanPhamViewDao.searchSanPhamByTrangThai(ma_cua_hang, matt);
    }

}
