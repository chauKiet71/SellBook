package com.example.booker.restController;

import com.example.booker.entity.TaiKhoanNganHang;
import com.example.booker.service.nguoidung.TaiKhoanNganHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tk-nganhang")
public class TaiKhoanNganHangRestController {

    @Autowired
    private TaiKhoanNganHangService taiKhoanNganHangService;

    @GetMapping("/{maCh}")
    public List<TaiKhoanNganHang> get(@PathVariable int maCh) {
        return taiKhoanNganHangService.get(maCh);
    }

    @PostMapping
    public TaiKhoanNganHang add(@RequestBody TaiKhoanNganHang nh) {
        return taiKhoanNganHangService.create(nh);
    }

    @PutMapping
    public TaiKhoanNganHang update(@RequestBody TaiKhoanNganHang nh) {
        return taiKhoanNganHangService.update(nh);
    }
}
