package com.example.booker.restController;

import com.example.booker.entity.GiaoDichCuaHang;
import com.example.booker.service.nguoidung.GiaoDichNguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/giaodich")
public class GiaoDichNguoiDungRestController {

    @Autowired
    GiaoDichNguoiDungService giaoDichNguoiDungService;

    @GetMapping
    public List<GiaoDichCuaHang> getGiaoDichCuaHang(){
        return giaoDichNguoiDungService.getGiaoDichCuaHang();
    }

    @GetMapping("/{ma_trang_thai}")
    public List<GiaoDichCuaHang> getGiaoDichCuaHang(@PathVariable int ma_trang_thai){
        return giaoDichNguoiDungService.getGIaoDichCuaHangByTrangThai(ma_trang_thai);
    }

    @PostMapping()
    public GiaoDichCuaHang create(@RequestBody GiaoDichCuaHang giaoDichCuaHang) {
        return giaoDichNguoiDungService.create(giaoDichCuaHang);
    }

    @PutMapping
    public GiaoDichCuaHang update(@RequestBody GiaoDichCuaHang giaoDichCuaHang) {
        return giaoDichNguoiDungService.create(giaoDichCuaHang);
    }
}
