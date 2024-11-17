package com.example.booker.dao;

import com.example.booker.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SanPhamDao extends JpaRepository<SanPham, Integer> {

    //Lay san pham theo the loai
    @Query("select s from SanPham s where s.the_loai.ma_the_loai = :ma_the_loai")
    List<SanPham> findByMaTheLoai(int ma_the_loai);

//    SELLER - lấy sản phẩm theo mã cửa hàng
    @Query("SELECT s FROM SanPham s " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang order by s.ma_san_pham desc")
    List<SanPham> getAllSanPhamByMaCuaHang(int ma_cua_hang);

    //Phương thức kiểm tra san da ton tai
    @Query("SELECT (COUNT(s) > 0) FROM SanPham s " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND s.ten_san_pham LIKE CONCAT('%', :tenSanPham, '%')")
    boolean existBySanPham(int ma_cua_hang, String tenSanPham);

    //Loc san pham theo gia tu thap den cao
    @Query("SELECT s FROM SanPham s " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang " +
            "ORDER BY s.gia asc")
    List<SanPham> findAllSanPhamSortPriceAsc(int ma_cua_hang);

    @Query("SELECT s FROM SanPham s " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang" +
            " ORDER BY s.gia desc")
    List<SanPham> findAllSanPhamSortPriceDesc(int ma_cua_hang);

    @Query("SELECT s FROM SanPham s " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang " +
            "ORDER BY s.diem_trung_binh desc")
    List<SanPham> getListProductOrderByComment(int ma_cua_hang);

    @Query("SELECT s FROM SanPham s " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang " +
           "ORDER BY s.da_ban desc")
    List<SanPham> getListProductOrderByDaBanDesc(int ma_cua_hang);

    @Query("SELECT s FROM SanPham s " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang " +
            "ORDER BY s.doanh_thu desc")
    List<SanPham> getListProductOrderByDoanhThuDesc(int ma_cua_hang);

//    ADMIN - lấy sách chờ duyệt
    @Query("SELECT s FROM SanPham s WHERE s.trang_thai_duyet = false ")
    List<SanPham> getListProductConHang();

//    ADMIN - lấy sách bị khóa
    @Query("SELECT s FROM SanPham s WHERE s.trang_thai_khoa = true ")
    List<SanPham> getListProductKhoa();

//    ADMIN - lấy sách còn hàng và hê hàng
    @Query("SELECT s FROM SanPham s WHERE s.trang_thai_hoat_dong = 1 OR s.trang_thai_hoat_dong = 2")
    List<SanPham> getListBookDangBan();

//    SELLER - lấy sách còn hàng
//    @Query("SELECT s FROM SanPham s " +
//            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
//            "WHERE c.ma_cua_hang = :ma_cua_hang " +
//            "ORDER BY s.da_ban desc")

}
