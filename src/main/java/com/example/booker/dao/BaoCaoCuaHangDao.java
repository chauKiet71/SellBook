package com.example.booker.dao;
import com.example.booker.entity.BaoCaoCuaHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  BaoCaoCuaHangDao extends JpaRepository<BaoCaoCuaHang, Integer> {
//     Phương thức get list bao cao theo id_nguoi bị báo cáo
@Query("SELECT b FROM BaoCaoCuaHang b WHERE b.id_tai_khoan_bi_bao_cao.id_tai_khoan = :idtaikhoan AND (b.trang_thai_bao_cao IS NULL OR b.trang_thai_bao_cao NOT IN (1, 4))")
List<BaoCaoCuaHang> findThongbaochotaikhoan(int idtaikhoan);


}
