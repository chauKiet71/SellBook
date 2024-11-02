package com.example.booker.dao;

import com.example.booker.entity.DonHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DonHangChiTietDao extends JpaRepository<DonHangChiTiet, Integer> {

    // lấy danh sách hóa đơn chi tiết của cửa hàng theo trạng thái
    @Query("SELECT dhct FROM SanPham s " +
            "JOIN DonHangChiTiet dhct " +
            "on s.ma_san_pham = dhct.san_pham.ma_san_pham" +
            " WHERE s.ma_cua_hang = :ma_cua_hang AND dhct.ma_trang_thai = :ma_trang_thai order by dhct.ma_don_hang_chi_tiet desc")
    List<DonHangChiTiet> findByMaTrangThaiAndMaCuaHang(int ma_cua_hang, int ma_trang_thai);

    //[NB] API lọc hóa đơn chi tiết theo theo ngày tạo
    @Query("SELECT dhct FROM DonHangChiTiet dhct " +
            "JOIN SanPham s " +
            "on s.ma_san_pham = dhct.san_pham.ma_san_pham " +
            "JOIN DonHang dh " +
            "on dh.ma_don_hang = dhct.don_hang.ma_don_hang " +
            "WHERE s.ma_cua_hang = :ma_cua_hang " +
            "AND dhct.ma_trang_thai = :ma_trang_thai " +
            "AND dh.ngay_tao = :ngay_tao order by dhct.don_hang.ngay_tao desc ")
    List<DonHangChiTiet> sortDonHangChiTietByNgayTao(int ma_cua_hang, int ma_trang_thai, LocalDate ngay_tao);

    //[NB] API lọc hóa đơn chi tiết theo mã hóa đơn
    @Query("SELECT dhct FROM DonHangChiTiet dhct " +
            "JOIN SanPham s " +
            "on s.ma_san_pham = dhct.san_pham.ma_san_pham " +
            "JOIN DonHang dh " +
            "on dh.ma_don_hang = dhct.don_hang.ma_don_hang " +
            "WHERE s.ma_cua_hang = :ma_cua_hang " +
            "AND dhct.ma_trang_thai = :ma_trang_thai " +
            "AND dh.ma_don_hang = :ma_don_hang")
    List<DonHangChiTiet> sortDonHangChiTietByMaDonHang(int ma_cua_hang, int ma_trang_thai, int ma_don_hang);

    //lấy thông tin chi tiết của một hóa đơn chi tiết
    @Query("SELECT dhct FROM DonHangChiTiet dhct " +
            "JOIN SanPham s " +
            "on s.ma_san_pham = dhct.san_pham.ma_san_pham " +
            "WHERE s.ma_cua_hang = :ma_cua_hang " +
            "AND dhct.ma_don_hang_chi_tiet = :ma_don_hang")
    DonHangChiTiet InfoDetailDonHangChiTiet(int ma_cua_hang, int ma_don_hang);

    @Query(value = "CALL GetDoanhThu(:ma_cua_hang, :ma_trang_thai)", nativeQuery = true)
    Double GetDoanhThu(@Param("ma_cua_hang") int maCuaHang, @Param("ma_trang_thai") int maTrangThai);

    //Đơn hàng moi theo 7 ngày gần nhất thuộc cửa hàng
    @Query(value = "SELECT dhct.* " +
            "FROM don_hang dh " +
            "JOIN don_hang_chi_tiet dhct ON dh.ma_don_hang = dhct.ma_don_hang " +
            "JOIN san_pham_view s ON s.ma_san_pham = dhct.ma_san_pham " +
            "JOIN voucher v ON v.id_voucher = dhct.id_voucher " +
            "WHERE dh.ngay_tao BETWEEN CURDATE() - INTERVAL 20 DAY AND CURDATE() " +
            "AND dhct.ma_trang_thai = :ma_trang_thai " +
            "AND s.ma_cua_hang = :ma_cua_hang", nativeQuery = true)
    List<DonHangChiTiet> findDonHangChiTiet(int ma_cua_hang, int ma_trang_thai);

//    Đếm số lượng đơnhangfgf theo mã cửa hàng và mã trạng thái
    @Query("SELECT count(dhct) FROM SanPham s " +
            "JOIN DonHangChiTiet dhct " +
            "on s.ma_san_pham = dhct.san_pham.ma_san_pham" +
            " WHERE s.ma_cua_hang = :ma_cua_hang AND dhct.ma_trang_thai = :ma_trang_thai order by dhct.ma_don_hang_chi_tiet desc")
    long countOrderDetailsByIdCHAndIdTT(int ma_cua_hang, int ma_trang_thai);

    @Query("SELECT hd FROM DonHangChiTiet hd JOIN SanPham s ON hd.san_pham.ma_san_pham = s.ma_san_pham WHERE s.ma_cua_hang = :maCuaHang AND hd.ma_trang_thai = :maTrangThai AND hd.ma_don_hang_chi_tiet = :maDonHangChiTiet")
    List<DonHangChiTiet> findOrderDetailByStoreAndStatus(
            @Param("maCuaHang") int maCuaHang,
            @Param("maTrangThai") int maTrangThai,
            @Param("maDonHangChiTiet") int maDonHangChiTiet);

}
