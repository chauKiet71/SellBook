package com.example.booker.service.nguoidung;



import com.example.booker.entity.SanPham;
import com.example.booker.entity.view.SanPhamView;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface SanPhamService {

    List<SanPhamView> findAll(int ma_san_pham);

    SanPham create(int ma_cua_hang, SanPham sanPham);

    List<SanPhamView> findByTenSanPham(int ma_san_pham, String tenSanPham);

    List<SanPhamView> findByTheLoai(int ma_cua_hang, int id);

    List<SanPhamView> findByCreateDate(int ma_cua_hang, LocalDate createDate);

    SanPham findById(int id);

    void deleteById(int id);

    SanPham update(SanPham sanPham);

    List<SanPham> sortPriceAsc(int ma_cua_hang);

    List<SanPham> sortPriceDesc(int ma_cua_hang);

    List<SanPhamView> sanPhamByTrangThaiKhoa(int ma_cua_hang);

    List<SanPhamView> sanPhamByChoDuyet(int ma_cua_hang);

    List<SanPhamView> sanPhamByHetHang(int ma_cua_hang);

    List<SanPhamView> sanPhamByConHang(int ma_cua_hang);

    List<SanPhamView> searchSanPhamByTrangThai(int ma_cua_hang, int matt);


    SanPham khoa_sanpham(int id, SanPham sanPham);

    //duyet san pham
    SanPham duyet_sanpham(int id, SanPham sanPham);

    //lấy ra sản phẩm theo theo lượt bán từ cao đến thấp
    List<SanPhamView> findAllSanPhamByLuotBan(int ma_cua_hang);

    //lấy ra sản phẩm bán chạy 7 ngày
    List<SanPhamView> sanPham7Day(int ma_cua_hang);
}
