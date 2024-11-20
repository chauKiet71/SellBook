package com.example.booker.restController;

import com.example.booker.dao.GiaoDichCuaHangDao;
import com.example.booker.entity.GiaoDichCuaHang;
import com.example.booker.service.nguoidung.GiaoDichCuaHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/giaodich")
public class GiaoDichCuaHangRestController {

    @Autowired
    GiaoDichCuaHangService giaoDichCuaHangService;

    @GetMapping
    public List<GiaoDichCuaHang> getGiaoDichCuaHang(){
        return giaoDichCuaHangService.getGiaoDichCuaHang();
    }

    @GetMapping("/{ma_trang_thai}")
    public List<GiaoDichCuaHang> getGiaoDichCuaHang(@PathVariable int ma_trang_thai){
        return giaoDichCuaHangService.getGIaoDichCuaHangByTrangThai(ma_trang_thai);
    }

    @PostMapping("/add")
    public GiaoDichCuaHang create(@RequestBody GiaoDichCuaHang giaoDichCuaHang) {
        return giaoDichCuaHangService.create(giaoDichCuaHang);
    }

    @PutMapping("/update")
    public GiaoDichCuaHang update(@RequestBody GiaoDichCuaHang giaoDichCuaHang) {
        return giaoDichCuaHangService.create(giaoDichCuaHang);
    }

}
