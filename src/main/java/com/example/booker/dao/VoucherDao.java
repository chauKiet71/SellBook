package com.example.booker.dao;

import com.example.booker.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoucherDao extends JpaRepository<Voucher, Integer> {

    @Query("SELECT v FROM Voucher v JOIN CuaHang c on c.ma_cua_hang = v.ma_cua_hang WHERE c.ma_cua_hang = ?1 ORDER BY v.id_voucher desc")
    public List<Voucher> findByCuaHang(Integer id);

    @Query("SELECT v FROM Voucher v JOIN CuaHang c on c.ma_cua_hang = v.ma_cua_hang WHERE c.ma_cua_hang = ?1 AND v.id_voucher = ?2")
    public Voucher findVoucherByMaVoucher(Integer idch, int ma_voucher);
}
