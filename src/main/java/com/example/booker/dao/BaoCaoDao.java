package com.example.booker.dao;

import com.example.booker.entity.BaoCao;
import com.example.booker.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BaoCaoDao extends JpaRepository<BaoCao, Integer> {
    // Phương thức get báo cáo từ cử hàng
    @Query("SELECT b FROM BaoCao b WHERE b.truong_hop = false ")
    List<BaoCao> findBaoCaoByCuaHang();

    //phương thức get báo cáo từ người dùng
    @Query("SELECT b FROM BaoCao b WHERE b.truong_hop = true ")
    List<BaoCao> findBaoCaoByNguoiDung();

    //phương thức get báo cáo từ chưa duyet từ cử hàng
    @Query("SELECT b FROM BaoCao b WHERE b.trang_thai_bao_cao = false and b.truong_hop = false")
    List<BaoCao> findBaoCaoByChuaDuyetCuaHang();

    //phương thức get báo cáo từ chưa duyet từ người dùng
    @Query("SELECT b FROM BaoCao b WHERE b.trang_thai_bao_cao = false and b.truong_hop = true")
    List<BaoCao> findBaoCaoByChuaDuyetNguoiDung();

    // Phương thức lấy thông báo cho người dùng theo id_tai_khoan_bi_bao_cao
    @Query("SELECT b FROM BaoCao b WHERE b.truong_hop = false AND b.an_thong_bao = false AND b.id_tai_khoan_bi_bao_cao.id_tai_khoan = :idTaiKhoan")
    List<BaoCao> findThongBaoNguoiDungById( int idTaiKhoan);

    //phương thức get thông báo cho cửa hàng
    @Query("SELECT b FROM BaoCao b WHERE b.truong_hop = true and b.trang_thai_bao_cao = true")
    List<BaoCao> findThongBaoCuaHang();
}
