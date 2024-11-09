package com.example.booker.service.nguoidung;

import com.example.booker.entity.TaiKhoanNganHang;
import org.springframework.stereotype.Service;

@Service
public interface TaiKhoanNganHangService {

    TaiKhoanNganHang create(TaiKhoanNganHang bank);

    TaiKhoanNganHang update(TaiKhoanNganHang tk);

}
