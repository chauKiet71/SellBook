package com.example.booker.dao;

import com.example.booker.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SanPhamDao extends JpaRepository<SanPham, Integer> {

    // Phương thức tìm kiếm sản phẩm theo tên không phân biệt hoa thường và trả về danh sách
    @Query("SELECT p FROM SanPham p WHERE p.ten_san_pham LIKE CONCAT('%', :tenSanPham, '%')")
    List<SanPham> findByTenSanPhamContainingIgnoreCase(String tenSanPham);

    //Phương thức kiểm tra san khong ton tai
    @Query("SELECT (COUNT(o) > 0) FROM SanPham o WHERE o.ten_san_pham LIKE CONCAT('%', :tenSanPham, '%')")
    boolean existBySanPham(String tenSanPham);

    //Lọc sản phẩm theo thể loại
    @Query("SELECT s FROM SanPham s JOIN TheLoai t on s.ma_the_loai = t.ma_the_loai WHERE t.ma_the_loai = :id")
    List<SanPham> findSanPhamByTheLoai(int id);

    //Lọc sản phẩm theo ngày tạo
    @Query("SELECT s FROM SanPham s WHERE s.ngay_tao = :createDate")
    List<SanPham> findSanPhamByCreateDate(LocalDate createDate);

//    @Query("SELECT s, DATE_FORMAT(s.ngay_tao, '%d-%m-%Y') FROM  SanPham s")
//    List<SanPham> findAllSanPham();

    //Loc san pham theo gia tu thap den cao
    @Query("SELECT s FROM SanPham s ORDER BY s.gia asc")
    List<SanPham> findAllSanPhamSortPriceAsc();

    @Query("SELECT s FROM SanPham s ORDER BY s.gia desc")
    List<SanPham> findAllSanPhamSortPriceDesc();
}
