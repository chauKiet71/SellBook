package com.example.booker.restController;

import com.example.booker.dao.*;
import com.example.booker.entity.DonHang;
import com.example.booker.entity.DonHangChiTiet;
import com.example.booker.service.nguoidung.DonHangService;
import com.example.booker.service.nguoidung.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/donhang")
public class DonHangRestController {

    @Autowired
    DonHangChiTietDao donHangChiTietDao;

    @Autowired
    DonHangDao donHangDao;

    @Autowired
    TaiKhoanDao taiKhoanDao;

    @Autowired
    DiaChiDao diaChiDao;

    @Autowired
    TrangThaiDonHangDao trangThaiDonHangDao;

    @Autowired
    VoucherService voucherService;

//  Hiển thị đơn hàng chi tiết theo người dùng
    @GetMapping("/taikhoan-{ma_tai_khoan}")
    public ResponseEntity<List<DonHangChiTiet>> getDonHangChiTiet(@PathVariable int ma_tai_khoan) {
        if (donHangChiTietDao.getSanPhamByTaiKhoan(ma_tai_khoan) != null) {
            return ResponseEntity.ok(donHangChiTietDao.getSanPhamByTaiKhoan(ma_tai_khoan));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
//  Lấy thông tin từng đơn hàng chi tiết
    @GetMapping("/dhct-{id}")
    public ResponseEntity<DonHangChiTiet> getById(@PathVariable int id) {
        if (donHangChiTietDao.existsById(id)) {
            return ResponseEntity.ok(donHangChiTietDao.findById(id).get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
//  Tạo đơn hàng mới và thêm đơn hàng chi tiết cho đơn hàng
    @PostMapping("/taikhoan-{tkid}/diachi-{dcid}")
    public ResponseEntity<List<DonHangChiTiet>> add(@RequestBody List<DonHangChiTiet> donHangChiTiets, @PathVariable int tkid, @PathVariable int dcid) {
//       Lưu đơn hàng của người dùng đang xử lí
        if ((taiKhoanDao.existsById(tkid)) && (diaChiDao.getListDiaChi(tkid).contains(diaChiDao.findById(dcid).get()))) {
            DonHang donHang = new DonHang();
            donHang.setTai_khoan(taiKhoanDao.findById(tkid).get());
            donHang.setDia_chi(diaChiDao.findById(dcid).get());
            donHang.setNgay_tao(new Date());
            donHangChiTiets.forEach(donHangChiTiet -> {
                if (voucherService.getVouchers(donHangChiTiet.getSan_pham().getMa_cua_hang()).contains(donHangChiTiet.getVoucher())) {
                    donHangChiTiet.setThanh_tien(donHangChiTiet.getGia() * donHangChiTiet.getSo_luong());
                    donHangChiTiet.setDon_hang(donHang);
                    donHangChiTiet.setTrang_thai(trangThaiDonHangDao.findById(11).get());
                }
            });
            donHangDao.save(donHang);
            donHangChiTietDao.saveAll(donHangChiTiets);
            return ResponseEntity.ok(donHangChiTiets);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
//  Cập nhật thông tin đơn hàng(chủ yếu số lượng)
    @PutMapping("/dhct-{id}")
    public ResponseEntity<DonHangChiTiet> updateDHCT(@RequestBody DonHangChiTiet dhct, @PathVariable int id) {
        if (donHangChiTietDao.existsById(id)) {
            dhct.setThanh_tien(dhct.getSo_luong() *  dhct.getGia());
            return ResponseEntity.ok(donHangChiTietDao.save(dhct));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
//  Xóa đơn hàng chi tiết khỏi đơn hàng
    @DeleteMapping("/dhct-{id}")
    public ResponseEntity<Void> deleteDHCT(@PathVariable int id) {
        if (donHangChiTietDao.existsById(id)) {
            donHangChiTietDao.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
//  Xóa đơn hàng nếu đơn hàng không còn sản phẩm sau khi xóa sản phẩm cuối cùng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonHang(@PathVariable int id) {
        if (donHangDao.findAll().isEmpty()) {
            donHangDao.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else {
            throw new RuntimeException("Đơn hàng vẫn còn sản phẩm");
        }
    }
}
