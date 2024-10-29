package com.example.booker.dao;


import com.example.booker.entity.view.SanPhamView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SanPhamViewDao extends JpaRepository<SanPhamView,Integer> {

    //Phương thức truy vấn sản phẩm theo cửa hàng
    @Query("SELECT s FROM SanPhamView s " +
            "LEFT JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang  ORDER BY s.ma_san_pham desc")
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

//    Lấy ra số lượng sản phẩm thuộc cửa hàng
    @Query("SELECT COUNT(s) FROM SanPhamView s WHERE s.ma_cua_hang = :maCuaHang")
    long countByMaCuaHang(int maCuaHang);

//  Lấy ra sản phẩm bị khoá
    @Query("SELECT s FROM SanPhamView s WHERE s.ma_cua_hang = :ma_cua_hang and s.trang_thai_hoat_dong = 0 ")
    List<SanPhamView> findAllSanPhamBiKhoa(int ma_cua_hang);

//  Lấy ra sản phẩm chờ duyệt
    @Query("SELECT s FROM SanPhamView s WHERE s.ma_cua_hang = :ma_cua_hang and s.trang_thai_hoat_dong = 3")
    List<SanPhamView> findAllSanPhamChoDuyet(int ma_cua_hang);

    //  Lấy ra sản phẩm hết hàng
    @Query("SELECT s FROM SanPhamView s WHERE s.ma_cua_hang = :ma_cua_hang and s.trang_thai_hoat_dong = 2")
    List<SanPhamView> findAllSanPhamHetHang(int ma_cua_hang);

//  Lấy ra sản phẩm còn hàng
    @Query("SELECT s FROM SanPhamView s WHERE s.ma_cua_hang = :ma_cua_hang and s.trang_thai_hoat_dong = 1")
    List<SanPhamView> findAllSanPhamConHang(int ma_cua_hang);


    // Tìm kiếm sản phẩm theo trang thai
    @Query("SELECT s FROM SanPhamView s WHERE s.ma_cua_hang = :ma_cua_hang and s.trang_thai_hoat_dong = :matt")
    List<SanPhamView> searchSanPhamByTrangThai(int ma_cua_hang, int matt);
}
