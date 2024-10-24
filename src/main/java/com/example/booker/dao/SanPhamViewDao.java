package com.example.booker.dao;


import com.example.booker.entity.SanPhamView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SanPhamViewDao extends JpaRepository<SanPhamView,Integer> {

    //Phương thức truy vấn sản phẩm theo cửa hàng
    @Query("SELECT s FROM SanPhamView s " +
            "LEFT JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang ORDER BY s.ma_san_pham desc")
    List<SanPhamView> findAllSanPham(int ma_cua_hang);

    // Phương thức tìm kiếm sản phẩm theo tên không phân biệt hoa thường và trả về danh sách
    @Query("SELECT p FROM SanPhamView p " +
            "JOIN CuaHang c on c.ma_cua_hang = p.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND p.ten_san_pham LIKE CONCAT('%', :tenSanPham, '%') ")
    List<SanPhamView> findByTenSanPhamContainingIgnoreCase(int ma_cua_hang, String tenSanPham);

    //Lọc sản phẩm theo thể loại
    @Query("SELECT s FROM SanPhamView s JOIN TheLoai t on s.ma_the_loai = t.ma_the_loai " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND t.ma_the_loai = :id")
    List<SanPhamView> findSanPhamByTheLoai(int ma_cua_hang,int id);

    //Lọc sản phẩm theo ngày tạo
    @Query("SELECT s FROM SanPhamView s" +
            " JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND s.ngay_tao = :createDate")
    List<SanPhamView> findSanPhamByCreateDate(int ma_cua_hang, LocalDate createDate);

}
