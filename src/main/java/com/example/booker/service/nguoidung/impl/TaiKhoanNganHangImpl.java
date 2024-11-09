package com.example.booker.service.nguoidung.impl;

import com.example.booker.dao.TaiKhoanNganHangDao;
import com.example.booker.entity.TaiKhoanNganHang;
import com.example.booker.service.nguoidung.TaiKhoanNganHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanNganHangImpl implements TaiKhoanNganHangService {

    @Autowired
    TaiKhoanNganHangDao taiKhoanNganHangDao;

    @Override
    public TaiKhoanNganHang create(TaiKhoanNganHang bank) {
        return taiKhoanNganHangDao.save(bank);
    }

    @Override
    public TaiKhoanNganHang update( TaiKhoanNganHang tk) {
        return taiKhoanNganHangDao.save(tk);
    }
}
