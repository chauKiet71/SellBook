package com.example.booker.dao;

import com.example.booker.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SanPhamDao extends JpaRepository<SanPham, Integer> {

    //Lay san pham theo the loai
    @Query("select s from SanPham s where s.ma_the_loai = :ma_the_loai")
    List<SanPham> findByMaTheLoai(int ma_the_loai);

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

//  Hiển thị sản phẩm theo từ khóa
    @Query("select s from SanPham s " +
            "where s.ten_san_pham like concat('%', :keyword, '%') ")
    List<SanPham> findSanPhamByTen(String keyword);

//  Hiển thị sản phẩm theo loại và khoảng giá
    @Query("select s from SanPham s " +
            "where (:theloais is null or s.ma_the_loai in :theloais) " +
            "and (:min is null or s.gia >= :min) " +
            "and (:max is null or s.gia <= :max)")
    List<SanPham> findSanPhamByTheloaiAndGia(List<Integer> theloais, Float min, Float max);

//  Hiển thị sản phẩm theo loại và khoảng giá sắp xếp theo ngày tạo mới nhất
    @Query("select s from SanPham s " +
            "where (:theloais is null or s.ma_the_loai in :theloais) " +
            "and (:min is null or s.gia >= :min) " +
            "and (:max is null or s.gia <= :max) " +
            "order by s.ngay_tao desc")
    List<SanPham> findSanPhamByTheloaiAndGiaSortByDate(List<Integer> theloais, Float min, Float max);

//  Hiển thị sản phẩm theo loại và khoảng giá sắp xếp theo lượt bán giảm dần
    @Query("select s from SanPham s " +
            "left join DonHangChiTiet dhct on dhct.san_pham.ma_san_pham = s.ma_san_pham and dhct.trang_thai.ma_trang_thai = 4 " +
            "where (:theloais is null or s.ma_the_loai in :theloais) " +
            "and (:min is null or s.gia >= :min) " +
            "and (:max is null or s.gia <= :max) " +
            "group by s.ma_san_pham " +
            "order by count(dhct.so_luong) desc")
    List<SanPham> findSanPhamByTheLoaiAndGiaSortByBuyCount(List<Integer> theloais, Float min, Float max);

//  Hiển thị sản phẩm theo loại và khoảng giá sắp xếp theo giá tăng dần
    @Query("select s from SanPham s " +
            "where (:theloais is null or s.ma_the_loai in :theloais) " +
            "and (:min is null or s.gia >= :min) " +
            "and (:max is null or s.gia <= :max) " +
            "order by s.gia")
    List<SanPham> findSanPhamByTheloaiAndGiaSortByGiaAesc(List<Integer> theloais, Float min, Float max);

//  Hiển thị sản phẩm theo loại và khoảng giá sắp xếp theo giá giảm dần
    @Query("select s from SanPham s " +
            "where (:theloais is null or s.ma_the_loai in :theloais) " +
            "and (:min is null or s.gia >= :min) " +
            "and (:max is null or s.gia <= :max) " +
            "order by s.gia desc")
    List<SanPham> findSanPhamByTheloaiAndGiaSortByGiaDesc(List<Integer> theloais, Float min, Float max);
}
