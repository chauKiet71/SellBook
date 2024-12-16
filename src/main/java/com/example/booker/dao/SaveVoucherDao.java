package com.example.booker.dao;

import com.example.booker.entity.SaveVoucher;
import com.example.booker.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaveVoucherDao extends JpaRepository<SaveVoucher, Integer> {

    @Query("SELECT s from SaveVoucher s WHERE s.voucher.ma_cua_hang = :ma_cua_hang and s.id_tai_khoan = :id_tk")
    List<SaveVoucher> findVoucherByMaCuaHang(int ma_cua_hang, int id_tk);
    @Query("SELECT s from SaveVoucher s WHERE s.tai_khoan.id_tai_khoan = :id_tk")
    List<SaveVoucher> findVoucherByIdtk(int id_tk);
}
