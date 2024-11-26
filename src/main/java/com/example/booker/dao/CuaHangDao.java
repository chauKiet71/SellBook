package com.example.booker.dao;

import com.example.booker.entity.CuaHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuaHangDao extends JpaRepository<CuaHang, Integer> {

//    ADMIN - laáy ửa hàng chờ duyệt
    @Query("select c from CuaHang c where c.trang_thai_cua_hang.ma_trang_thai_cua_hang = :maTrangThai")
    List<CuaHang> getCuaHangByTrangThai(@Param("maTrangThai") Integer maTrangThai);

//    SELLER - lấy doanh thu cửa hàng
//    @Query("select ch.doanh_thu from CuaHang ch where ch.ma_cua_hang = :")

// ADMIN - ấy tất cả cửa hàng sắp xếp theo doanh thu từ cao ddeesns thấp
    @Query("select c from CuaHang c order by c.doanh_thu desc ")
    List<CuaHang> getCuaHangDoanhThu();

    //    ADMIN - lấy cua hang vi pham
    @Query("select c from CuaHang c where c.tong_diem_vi_pham >= 24")
    List<CuaHang> getCuaHangvipham();

}
