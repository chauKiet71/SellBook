package com.example.booker.service.nguoidung.impl;

import com.example.booker.dao.TaiKhoanNganHangDao;
import com.example.booker.entity.TaiKhoanNganHang;
import com.example.booker.service.nguoidung.TaiKhoanNganHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanNganHangServiceImpl implements TaiKhoanNganHangService {

    @Autowired
    TaiKhoanNganHangDao taiKhoanNganHangDao;

    @Override
    public List<TaiKhoanNganHang> get(int ma_cua_hang) {
        return taiKhoanNganHangDao.findByMaCuaHang(ma_cua_hang);
    }

    @Override
    public TaiKhoanNganHang create(TaiKhoanNganHang tk) {
        return taiKhoanNganHangDao.save(tk);
    }

    @Override
    public TaiKhoanNganHang update(TaiKhoanNganHang tk) {
        return taiKhoanNganHangDao.save(tk);
    }
}
