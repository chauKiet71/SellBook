package com.example.booker.dao;

import com.example.booker.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SanPhamDao extends JpaRepository<SanPham, Integer> {

    //Lay san pham theo the loai
    @Query("select s from SanPham s where s.the_loai.ma_the_loai = :ma_the_loai")
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

//    ADMIN - lấy sách chờ duyệt
    @Query("SELECT s FROM SanPham s WHERE s.trang_thai_duyet = false ")
    List<SanPham> getListProductConHang();

//    ADMIN - lấy sách bị khóa
    @Query("SELECT s FROM SanPham s WHERE s.trang_thai_khoa = true ")
    List<SanPham> getListProductKhoa();

//    ADMIN - lấy sách còn hàng và hê hàng
    @Query("SELECT s FROM SanPham s WHERE s.trang_thai_hoat_dong = 1 OR s.trang_thai_hoat_dong = 2")
    List<SanPham> getListBookDangBan();


    //Phương thức truy vấn sản phẩm theo cửa hàng
    @Query("SELECT s FROM SanPham s " +
            "LEFT JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang  ORDER BY s.ma_san_pham desc")
    List<SanPham> findAllSanPham(int ma_cua_hang);

    // Phương thức tìm kiếm sản phẩm theo tên không phân biệt hoa thường và trả về danh sách
    @Query("SELECT p FROM SanPham p " +
            "JOIN CuaHang c on c.ma_cua_hang = p.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND p.ten_san_pham LIKE CONCAT('%', :tenSanPham, '%') ")
    List<SanPham> findByTenSanPhamContainingIgnoreCase(int ma_cua_hang, String tenSanPham);

    //Lọc sản phẩm theo thể loại
    @Query("SELECT s FROM SanPham s JOIN TheLoai t on s.the_loai.ma_the_loai = t.ma_the_loai " +
            "JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND t.ma_the_loai = :id")
    List<SanPham> findSanPhamByTheLoai(int ma_cua_hang, int id);

    //Lọc sản phẩm theo ngày tạo
    @Query("SELECT s FROM SanPham s" +
            " JOIN CuaHang c on c.ma_cua_hang = s.ma_cua_hang " +
            "WHERE c.ma_cua_hang = :ma_cua_hang AND s.ngay_tao = :createDate")
    List<SanPham> findSanPhamByCreateDate(int ma_cua_hang, LocalDate createDate);

    //    Lấy ra số lượng sản phẩm thuộc cửa hàng
    @Query("SELECT COUNT(s) FROM SanPham s WHERE s.ma_cua_hang = :maCuaHang")
    long countByMaCuaHang(int maCuaHang);

    //  Lấy ra sản phẩm bị khoá
    @Query("SELECT s FROM SanPham s WHERE s.ma_cua_hang = :ma_cua_hang and s.trang_thai_hoat_dong = 0 ")
    List<SanPham> findAllSanPhamBiKhoa(int ma_cua_hang);

    //  Lấy ra sản phẩm chờ duyệt
    @Query("SELECT s FROM SanPham s WHERE s.ma_cua_hang = :ma_cua_hang and s.trang_thai_hoat_dong = 3")
    List<SanPham> findAllSanPhamChoDuyet(int ma_cua_hang);

    //  Lấy ra sản phẩm hết hàng
    @Query("SELECT s FROM SanPham s WHERE s.ma_cua_hang = :ma_cua_hang and s.trang_thai_hoat_dong = 2")
    List<SanPham> findAllSanPhamHetHang(int ma_cua_hang);

    //  Lấy ra sản phẩm còn hàng
    @Query("SELECT s FROM SanPham s WHERE s.ma_cua_hang = :ma_cua_hang and s.trang_thai_hoat_dong = 1")
    List<SanPham> findAllSanPhamConHang(int ma_cua_hang);


    // Tìm kiếm sản phẩm theo trang thai
    @Query("SELECT s FROM SanPham s WHERE s.ma_cua_hang = :ma_cua_hang and s.trang_thai_hoat_dong = :matt")
    List<SanPham> searchSanPhamByTrangThai(int ma_cua_hang, int matt);

    // danh sách sản phẩm thuộc cửa hàng sắp xếp theo lượt bán từ cao đến thấp
    @Query("select s from SanPham s " +
            "where s.ma_cua_hang = :ma_cua_hang " +
            "order by s.da_ban desc ")
    List<SanPham> findAllSanPhamByLuotBan(int ma_cua_hang);


    //lấy ra danh sách sản phẩm bán chạy nhất trong 7 ngày gần nhất
//    @Query( "SELECT * FROM san_pham_view s " +
//            "JOIN don_hang_chi_tiet dhct " +
//            "ON dhct.ma_san_pham = s.ma_san_pham " +
//            "JOIN don_hang dh ON dh.ma_don_hang = dhct.ma_don_hang " +
//            "WHERE s.ma_cua_hang = :maCuaHang " +
//            "AND dh.ngay_tao BETWEEN CURDATE() - INTERVAL 7 DAY AND CURDATE() " +
//            "ORDER BY s.da_ban DESC")
//    List<SanPham> sanPham7Day(int ma_cua_hang);


//    @Query(value = "SELECT s.* FROM S s " +
//            "JOIN don_hang_chi_tiet dhct " +
//            "ON dhct.ma_san_pham = s.ma_san_pham " +
//            "JOIN don_hang dh ON dh.ma_don_hang = dhct.ma_don_hang " +
//            "WHERE s.ma_cua_hang = :ma_cua_hang " +
//            "AND dh.ngay_tao BETWEEN CURDATE() - INTERVAL 7 DAY AND CURDATE() " +
//            "GROUP BY " +
//            "s.ma_san_pham," +
//            "s.ten_san_pham, " +
//            "s.anh_san_pham, " +
//            "s.tac_gia, " +
//            "s.ten_the_loai ," +
//            "s.ma_the_loai, " +
//            "s.gia, " +
//            "s.trang_thai_duyet, " +
//            "s.trang_thai_khoa, " +
//            "s.trang_thai_hoat_dong ," +
//            "s.ngay_tao ," +
//            "s.so_luong_hang ," +
//            "s.ma_cua_hang, " +
//            "s.da_ban, " +
//            "s.doanh_thu, " +
//            "s.danh_gia " +
//            "ORDER BY s.da_ban DESC", nativeQuery = true)
//    List<SanPham> sanPham7Day(int ma_cua_hang);
}
