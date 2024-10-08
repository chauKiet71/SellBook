package com.example.booker.dao;

import com.example.booker.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SanPhamDao extends JpaRepository<SanPham, Integer> {

    //Phương thức truy vấn sản phẩm theo cửa hàng
    @Query("SELECT s FROM SanPham s " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang")
    List<SanPham> findAllSanPhamByCuahang(int ma_cua_hang);

    // Phương thức tìm kiếm sản phẩm theo tên không phân biệt hoa thường và trả về danh sách
    @Query("SELECT p FROM SanPham p " +
            "JOIN CuaHang c on c.ma_cua_hang = p.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND p.ten_san_pham LIKE CONCAT('%', :tenSanPham, '%') ")
    List<SanPham> findByTenSanPhamContainingIgnoreCase(int ma_cua_hang, String tenSanPham);

    //Phương thức kiểm tra san da ton tai
    @Query("SELECT (COUNT(s) > 0) FROM SanPham s " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND s.ten_san_pham LIKE CONCAT('%', :tenSanPham, '%')")
    boolean existBySanPham(int ma_cua_hang, String tenSanPham);

    //Lọc sản phẩm theo thể loại
    @Query("SELECT s FROM SanPham s JOIN TheLoai t on s.ma_the_loai = t.ma_the_loai " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND t.ma_the_loai = :id")
    List<SanPham> findSanPhamByTheLoai(int ma_cua_hang,int id);

    //Lọc sản phẩm theo ngày tạo
    @Query("SELECT s FROM SanPham s" +
            " JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND s.ngay_tao = :createDate")
    List<SanPham> findSanPhamByCreateDate(int ma_cua_hang, LocalDate createDate);

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
}
