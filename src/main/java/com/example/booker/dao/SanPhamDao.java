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

    //Tìm kiếm sản phẩm theo tên sản phẩm
    @Query("select s from SanPham s " +
            "where s.ten_san_pham like concat('%', :tenSanPham, '%')")
    List<SanPham> findAllSanPhamByTen(String tenSanPham);

    //Sắp xếp sản phẩm theo ngày tạo mới nhất
    @Query("select s from SanPham s " +
            "where s.ma_the_loai in :theloais and (s.gia >= :min and s.gia <= :max) " +
            "order by s.ngay_tao desc")
    List<SanPham> findAllByTheLoaiAndGia(List<Integer> theloais, float min, float max);

}
