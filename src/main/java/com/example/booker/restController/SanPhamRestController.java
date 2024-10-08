package com.example.booker.restController;

import com.example.booker.dao.SanPhamDao;
import com.example.booker.dto.request.ApiResponse;
import com.example.booker.entity.SanPham;
import com.example.booker.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/product")
public class SanPhamRestController {

    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    private SanPhamDao sanPhamDao;

    @GetMapping("/cuahang-{id}")
    public List<SanPham> getSp(@PathVariable int id) {
        return sanPhamService.findAll(id);
    }

    @PostMapping("/cuahang-{id}")
    public ApiResponse<SanPham> create(@PathVariable int id, @RequestBody SanPham sanPham) {
        ApiResponse<SanPham> response = new ApiResponse<>();
        response.setResult(sanPhamService.create(id, sanPham));
        return response;
    }

    @PutMapping("/cuahang-{id}/{idsp}")
    public SanPham update(@PathVariable Integer idsp, @RequestBody SanPham sanPham) {
        return sanPhamService.update(sanPham);
    }

    @DeleteMapping("/cuahang-{id}/{idsp}")
    public ResponseEntity<ApiResponse<Void>> deleteSanPham(@PathVariable int idsp) {
        sanPhamService.deleteById(idsp);
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Đã xóa thành công");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cuahang-{id}/{idsp}")
    public SanPham get(@PathVariable Integer idsp) {
        return sanPhamService.findById(idsp);
    }

    @GetMapping("/cuahang-{id}/tim-kiem/tensanpham")
    public List<SanPham> SearchSanPhamByTenSanPham(@PathVariable int id, @RequestParam String ten){
        if (!sanPhamDao.existBySanPham(id, ten)) {
            throw new RuntimeException("Product not found");
        }
        return sanPhamService.findByTenSanPham(id, ten);
    }

    @GetMapping("/cuahang-{id}/tim-kiem/theloai")
    public List<SanPham> SearchSanPhamByTheLoai(@PathVariable int id, @RequestParam int category){
        return sanPhamService.findByTheLoai(id, category);
    }

    @GetMapping("/cuahang-{id}/tim-kiem/ngay-tao")
    public ResponseEntity<List<SanPham>> searchCreateDate(@PathVariable int id,
                                                          @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        List<SanPham> sanPhams = sanPhamService.findByCreateDate(id, date);
        System.out.println(sanPhams);
        return ResponseEntity.ok(sanPhams);
    }

    @GetMapping("/cuahang-{id}/price-asc")
    public List<SanPham> sortPriceAsc(@PathVariable int id) {
        return sanPhamService.sortPriceAsc(id);
    }

    @GetMapping("/cuahang-{id}/price-desc")
    public List<SanPham> sortPriceDesc(@PathVariable int id) {
        return sanPhamService.sortPriceDesc(id);
    }
}
