package com.example.booker.service.nguoidung;

import com.example.booker.entity.TaiKhoanNganHang;

import java.util.List;

public interface TaiKhoanNganHangService {

    List<TaiKhoanNganHang> get(int ma_cua_hang);

    TaiKhoanNganHang create(TaiKhoanNganHang tk);

    TaiKhoanNganHang update(TaiKhoanNganHang tk);
}
