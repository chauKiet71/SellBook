package com.example.booker.service.nguoidung;



import com.example.booker.entity.CuaHang;

import java.util.List;

public interface CuaHangService {

    CuaHang createCuaHang(CuaHang cuaHang);

    CuaHang updateCuaHang(CuaHang cuaHang);

    List<CuaHang> getAllCuaHang();

    CuaHang getCuaHangById(int id);

    void deleteCuaHang(int id);
}
