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

    @GetMapping()
    public List<SanPham> getSp() {
        return sanPhamService.findAll();
    }

    @PostMapping()
    public ApiResponse<SanPham> create(@RequestBody SanPham sanPham) {
        ApiResponse<SanPham> response = new ApiResponse<>();
        response.setResult(sanPhamService.create(sanPham));
        return response;
    }

    @PutMapping("{id}")
    public SanPham update(@PathVariable Integer id, @RequestBody SanPham sanPham) {
        return sanPhamService.update(sanPham);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSanPham(@PathVariable int id) {
        sanPhamService.deleteById(id);
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Đã xóa thành công");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public SanPham get(@PathVariable Integer id) {
        return sanPhamService.findById(id);
    }

    @GetMapping("/tim-kiem/tensampham")
    public List<SanPham> SearchSanPhamByTenSanPham(@RequestParam String ten){
        if (!sanPhamDao.existBySanPham(ten)) {
            throw new RuntimeException("Product not found");
        }
        return sanPhamService.findByTenSanPham(ten);
    }

    @GetMapping("/tim-kiem/theloai")
    public List<SanPham> SearchSanPhamByTheLoai(@RequestParam int id){
        return sanPhamService.findByTheLoai(id);
    }

    @GetMapping("/tim-kiem/ngay-tao")
    public ResponseEntity<List<SanPham>> searchCreateDate(
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        List<SanPham> sanPhams = sanPhamService.findByCreateDate(date);
        System.out.println(sanPhams);
        return ResponseEntity.ok(sanPhams);
    }

    @GetMapping("/price-asc")
    public List<SanPham> sortPriceAsc() {
        return sanPhamService.sortPriceAsc();
    }

    @GetMapping("/price-desc")
    public List<SanPham> sortPriceDesc() {
        return sanPhamService.sortPriceDesc();
    }
}
