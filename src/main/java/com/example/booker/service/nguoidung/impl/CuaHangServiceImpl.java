package com.example.booker.service.nguoidung.impl;

import com.example.booker.dao.CuaHangDao;
import com.example.booker.entity.CuaHang;
import com.example.booker.service.nguoidung.CuaHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuaHangServiceImpl implements CuaHangService {

    @Autowired
    private CuaHangDao cuaHangDao;

    @Override
    public CuaHang createCuaHang(CuaHang cuaHang) {
        return cuaHangDao.save(cuaHang);
    }

    @Override
    public CuaHang updateCuaHang(CuaHang cuaHang) {
        return cuaHangDao.save(cuaHang);
    }

    @Override
    public List<CuaHang> getAllCuaHang() {
        return cuaHangDao.findAll();
    }

    @Override
    public CuaHang getCuaHangById(int id) {
        return cuaHangDao.findById(id).get();
    }

    @Override
    public void deleteCuaHang(int id) {
        cuaHangDao.deleteById(id);
    }
}
