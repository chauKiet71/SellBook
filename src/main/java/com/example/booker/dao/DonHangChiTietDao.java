package com.example.booker.dao;

import com.example.booker.entity.DonHangChiTiet;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonHangChiTietDao extends JpaRepository<DonHangChiTiet, Integer> {

    //load gio hang
    @Query("select dhct from DonHangChiTiet dhct " +
            "join DonHang dh on dhct.don_hang.ma_don_hang = dh.ma_don_hang " +
            "where dh.tai_khoan.id_tai_khoan = :ma_tai_khoan")
    List<DonHangChiTiet> getSanPhamByTaiKhoan(int ma_tai_khoan);
    //  Lấy danh sách đơn hàng theo trạng thái
    @Query("select dhct from DonHangChiTiet dhct " +
            "join DonHang dh on dhct.don_hang.ma_don_hang = dh.ma_don_hang " +
            "join SanPham sp on dhct.san_pham.ma_san_pham = sp.ma_san_pham " +
            "where dhct.trang_thai.ma_trang_thai = :ma_trang_thai and dh.tai_khoan.id_tai_khoan = :ma_tai_khoan")
    List<DonHangChiTiet> getDonHangChiTietByTTAndTK(int ma_tai_khoan, int ma_trang_thai);
}
