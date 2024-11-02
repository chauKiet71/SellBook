package com.example.booker.restController;

import com.example.booker.dao.DiaChiDao;
import com.example.booker.dao.DonHangChiTietDao;
import com.example.booker.dao.DonHangDao;
import com.example.booker.dao.TaiKhoanDao;
import com.example.booker.entity.DiaChi;
import com.example.booker.entity.DonHangChiTiet;
import com.example.booker.entity.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/nguoidung")
public class NguoiDungRestController {
    @Autowired
    TaiKhoanDao taikhoanDao;
    @Autowired
    DonHangChiTietDao donHangChiTietDao;
    @Autowired
    DiaChiDao diaChiDao;

    @GetMapping("/{id}")
    public ResponseEntity<TaiKhoan> getThongTin(@PathVariable int id) {
        if (taikhoanDao.existsById(id)) {
            return ResponseEntity.ok(taikhoanDao.findById(id).get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
//  Cập nhật thông tin tài khoản(bao gồm đổi mật khẩu)
    @PutMapping("/{id}")
    public ResponseEntity<TaiKhoan> updateThongTin(@RequestBody TaiKhoan taikhoan, @PathVariable int id) {
        return ResponseEntity.ok(taikhoanDao.save(taikhoan));
    }
    @GetMapping("/diachi-{id}")
    public ResponseEntity<DiaChi> getDiaChi(@PathVariable int id) {
        return ResponseEntity.ok(diaChiDao.findById(id).get());
    }
//  Lấy danh sách địa chỉ
    @GetMapping("/diachi/{nguoidung_id}")
    public ResponseEntity<List<DiaChi>> getDanhSachDiaChi(@PathVariable int nguoidung_id) {
        if (taikhoanDao.existsById(nguoidung_id)) {
            return ResponseEntity.ok(diaChiDao.getListDiaChi(nguoidung_id));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
//  Cập nhật địa chỉ
    @PutMapping("/{nguoidung_id}/diachi-{dia_chi_id}")
    public ResponseEntity<DiaChi> updateDiaChi(@RequestBody DiaChi diaChi, @PathVariable int nguoidung_id, @PathVariable int dia_chi_id) {
        if (diaChiDao.existsById(dia_chi_id)) {
            return ResponseEntity.ok(diaChiDao.save(diaChi));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{nguoidung_id}/diachi-{dia_chi_id}")
    public ResponseEntity<Void> deleteDiaChi(@PathVariable int nguoidung_id, @PathVariable int dia_chi_id) {
        if (diaChiDao.existsById(dia_chi_id)) {
            diaChiDao.deleteById(dia_chi_id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
//  Lịch sử đơn hàng
    @GetMapping("/{id}/trangthai-{ma_trang_thai}")
    public ResponseEntity<List<DonHangChiTiet>> getDonHangChiTietByTrangThai(@PathVariable int id, @PathVariable int ma_trang_thai) {
        if (!donHangChiTietDao.getDonHangChiTietByTTAndTK(id, ma_trang_thai).isEmpty()) {
            return ResponseEntity.ok(donHangChiTietDao.getDonHangChiTietByTTAndTK(id, ma_trang_thai));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
