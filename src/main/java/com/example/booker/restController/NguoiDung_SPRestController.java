package com.example.booker.restController;

import com.example.booker.dao.SanPhamDao;
import com.example.booker.entity.SanPham;
import com.example.booker.service.nguoidung.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    @GetMapping("/{minPrice}-{maxPrice}/orderBy-{orderBy}/theloai")
    public ResponseEntity<List<SanPham>> getSanPhamByTheLoaiAndGia(@RequestParam(value = "ma_the_loai", required = false) List<Integer> the_loais,
                                   @PathVariable(value = "minPrice", required = false) Float minPrice, @PathVariable(value = "maxPrice", required = false) Float maxPrice, @PathVariable("orderBy") String orderBy) {
        return ResponseEntity.ok(sanPhamService.findSanPhamByTheLoaiAndGiaOrderBy(the_loais.isEmpty() ? null : the_loais, minPrice, maxPrice, orderBy));
    }
}
