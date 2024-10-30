package com.example.booker.restController;

import com.example.booker.dao.DonHangChiTietDao;
import com.example.booker.dao.DonHangDao;
import com.example.booker.entity.DonHang;
import com.example.booker.entity.DonHangChiTiet;
import com.example.booker.service.nguoidung.DonHangService;
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
    DonHangService donHangService;

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
//  Thêm sản phẩm vào đơn hàng
    @PostMapping
    public ResponseEntity<DonHangChiTiet> add(@RequestBody DonHangChiTiet donHangChiTiet, @RequestBody DonHang donHang) {
//       Lưu đơn hàng của người dùng đang xử lí(chưa bấm nút đặt hàng) vào session để lấy thông tin đơn hàng và set vào hàm
        if (donHangService.isExistDonHang(donHang.getTai_khoan().getId_tai_khoan(), donHang.getNgay_tao(), 2)) {
            donHangChiTiet.setSo_luong(donHangChiTiet.getSo_luong() + 1);
            donHangChiTietDao.save(donHangChiTiet);
        }
        else {
            donHang.setNgay_tao(new Date());
            donHangDao.save(donHang);
            donHangChiTietDao.save(donHangChiTiet);
        }
        return ResponseEntity.ok(donHangChiTiet);
    }
//  Cập nhật thông tin đơn hàng(chủ yếu số lượng)
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDHCT(@PathVariable int id) {
        if (donHangChiTietDao.existsById(id)) {
            donHangChiTietDao.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
