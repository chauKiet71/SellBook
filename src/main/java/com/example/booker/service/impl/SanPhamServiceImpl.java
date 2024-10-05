package com.example.booker.service.impl;

import com.example.booker.dao.SanPhamDao;
import com.example.booker.entity.SanPham;
import com.example.booker.service.SanPhamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamDao sanPhamDao;

    @Override
    public List<SanPham> findAll() {
        return sanPhamDao.findAll();
    }

    @Override
    public SanPham create(SanPham sanPham) {
        //kiem tra san pham ton tai
        if (sanPhamDao.existBySanPham(sanPham.getTen_san_pham()))
            throw new RuntimeException("Product not found");
        return sanPhamDao.save(sanPham);
    }

    @Override
    public List<SanPham> findByTenSanPham(String tenSanPham) {
        return sanPhamDao.findByTenSanPhamContainingIgnoreCase(tenSanPham);
    }

    @Override
    public List<SanPham> findByTheLoai(int id) {
        return sanPhamDao.findSanPhamByTheLoai(id);
    }

    @Override
    public List<SanPham> findByCreateDate(LocalDate createDate) {
        return sanPhamDao.findSanPhamByCreateDate(createDate);
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
}
