package com.example.booker.restController;

import com.example.booker.dao.DonHangChiTietDao;
import com.example.booker.dao.DonHangDao;
import com.example.booker.dao.TaiKhoanDao;
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

    @PutMapping("/{id}")
    public ResponseEntity<TaiKhoan> updateThongTin(@RequestBody TaiKhoan taikhoan, @PathVariable int id) {
        return ResponseEntity.ok(taikhoanDao.save(taikhoan));
    }

    @GetMapping("/{id}-{ma_trang_thai}")
    public ResponseEntity<List<DonHangChiTiet>> getDonHangChiTietByTrangThai(@PathVariable int id, @PathVariable int ma_trang_thai) {
        if (!donHangChiTietDao.getDonHangChiTietByTTAndTK(id, ma_trang_thai).isEmpty()) {
            return ResponseEntity.ok(donHangChiTietDao.getDonHangChiTietByTTAndTK(id, ma_trang_thai));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
