package com.example.booker.service.nguoidung;



import com.example.booker.entity.CuaHang;
import com.example.booker.entity.view.CuaHangView;

import java.util.List;

public interface CuaHangService {

    CuaHang createCuaHang(CuaHang cuaHang);

    CuaHang updateCuaHang(CuaHang cuaHang);

    List<CuaHang> getAllCuaHang();

    CuaHangView getCuaHangById(int id);

    void deleteCuaHang(int id);
}
