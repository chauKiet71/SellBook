package com.example.booker.dao;

import com.example.booker.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaChiDao extends JpaRepository<DiaChi, Integer> {
    @Query("select dc from DiaChi dc " +
            "where dc.tai_khoan.id_tai_khoan = :ma_tk")
    List<DiaChi> getListDiaChi(int ma_tk);

    //    Lấy địa chỉ mặc định theo tài khoản
    @Query("select dc from DiaChi dc " +
            "join TaiKhoan tk on dc.tai_khoan.id_tai_khoan = tk.id_tai_khoan " +
            "where tk.id_tai_khoan = :id_tk and dc.dia_chi_mac_dinh = true")
    DiaChi getDefaultDiaChi(int id_tk);
}
