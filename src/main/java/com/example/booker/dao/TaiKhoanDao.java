package com.example.booker.dao;

import com.example.booker.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaiKhoanDao extends JpaRepository<TaiKhoan, Integer> {
    // Phương thức kiểm tra sự tồn tại của tài khoản theo email
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TaiKhoan t WHERE t.email = :email")
    boolean existsByEmail(@Param("email") String email);
    Optional<TaiKhoan> findByEmail(String email);

//    ADMIN - lấy tài khoản có vai trò: user, seller
    @Query("select t from TaiKhoan t join VaiTro v on v.ma_vai_tro = t.vai_tro.ma_vai_tro where t.vai_tro.ma_vai_tro = 1 or t.vai_tro.ma_vai_tro = 2")
    List<TaiKhoan> findAllCustomer();

    //    ADMIN - đếm so lượng khách hàng theo trạng thái hoạt động
    @Query("select count(kh) from TaiKhoan kh where kh.trang_thai_tk = false")
    Long countTaiKhoanByFalse();

    //    ADMIN - đếm so lượng khách hàng theo trạng thái v hiệu hóa
    @Query("select count(kh) from TaiKhoan kh where kh.trang_thai_tk = true")
    Long countTaiKhoanByTrue();

//    ADMIN - hàm lấy danh sách khách hàng theo trạng thái
    @Query("select tk from TaiKhoan tk where tk.trang_thai_tk = :trangThai")
    List<TaiKhoan> findByTrangThai(@Param("trangThai") Boolean trangThai);

}
