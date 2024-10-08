package com.example.booker.service.impl;

import com.example.booker.dao.SanPhamDao;
import com.example.booker.entity.SanPham;
import com.example.booker.service.SanPhamService;
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

    @Override
    public List<SanPham> findAll(int ma_san_pham) {
        return sanPhamDao.findAllSanPhamByCuahang(ma_san_pham);
    }

    @Override
    public SanPham create(int ma_cua_hang, SanPham sanPham) {
        //kiem tra san pham ton tai
        if (sanPhamDao.existBySanPham(ma_cua_hang, sanPham.getTen_san_pham()))
            throw new RuntimeException("Product exist");
        return sanPhamDao.save(sanPham);
    }

    @Override
    public List<SanPham> findByTenSanPham(int ma_san_pham, String tenSanPham) {
        return sanPhamDao.findByTenSanPhamContainingIgnoreCase(ma_san_pham, tenSanPham);
    }

    @Override
    public List<SanPham> findByTheLoai(int ma_cua_hang, int id) {
        return sanPhamDao.findSanPhamByTheLoai(ma_cua_hang, id);
    }

    @Override
    public List<SanPham> findByCreateDate(int ma_cua_hang, LocalDate createDate) {
        return sanPhamDao.findSanPhamByCreateDate(ma_cua_hang, createDate);
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

}
