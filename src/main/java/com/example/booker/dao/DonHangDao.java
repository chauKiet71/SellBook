package com.example.booker.dao;

import com.example.booker.entity.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface DonHangDao extends JpaRepository<DonHang, Integer> {
    @Query("select dh.ma_don_hang, dh.ngay_tao, dh.tai_khoan, dh.dia_chi, dh.donHangChiTiets from DonHang dh " +
            "join DonHangChiTiet dhct " +
            "on dh.ma_don_hang = dhct.don_hang.ma_don_hang " +
            "where " +
            "dh.tai_khoan.id_tai_khoan = :ma_tai_khoan and dh.ngay_tao = :createDate and dhct.trang_thai.ma_trang_thai = :trang_thai")
    DonHang getDonHangByTaikhoanAndCreatedate(int ma_tai_khoan, Date createDate, int trang_thai);
}