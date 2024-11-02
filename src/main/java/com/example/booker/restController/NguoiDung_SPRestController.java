package com.example.booker.restController;

import com.example.booker.dao.SanPhamDao;
import com.example.booker.entity.SanPham;
import com.example.booker.service.nguoidung.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/sanpham")
public class NguoiDung_SPRestController {

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    SanPhamDao sanPhamDao;

    @GetMapping("/{keyword}")
    public ResponseEntity<List<SanPham>> getSanPhamByKeyword(@PathVariable String keyword) {
        if (sanPhamService.findSanPhamByKeyword(keyword) != null) {
            return ResponseEntity.ok(sanPhamService.findSanPhamByKeyword(keyword));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{minPrice}-{maxPrice}/theloai")
    public ResponseEntity<List<SanPham>> getSanPhamByTheLoaiAndGia(@RequestParam("ma_the_loai") List<Integer> the_loais,
                                       @PathVariable("minPrice") float minPrice, @PathVariable("maxPrice") float maxPrice) {
        return ResponseEntity.ok(sanPhamService.findSanPhamByTheLoaiAndGia(the_loais, minPrice, maxPrice));
    }
}
