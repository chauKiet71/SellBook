package com.example.booker.restController;

import com.example.booker.dao.SanPhamDao;
import com.example.booker.entity.SanPham;
import com.example.booker.entity.SanPhamView;
import com.example.booker.service.nguoidung.SanPhamService;
import com.example.booker.service.nguoidung.SaveFileExcelService;
import com.example.booker.request.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/product")
public class SanPhamRestController {

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    private SaveFileExcelService saveFileExcelService;

    @Autowired
    private SanPhamDao sanPhamDao;

    @GetMapping("/allinfo")
    public List<SanPham> getAllSanPham(){
        return sanPhamDao.findAll();
    }

    @GetMapping("/cuahang-{id}")
    public List<SanPhamView> getSp(@PathVariable int id) {
        return sanPhamService.findAll(id);
    }

    @PostMapping("/cuahang-{id}")
    public ApiResponse<SanPham> create(@PathVariable int id, @RequestBody SanPham sanPham) {
        ApiResponse<SanPham> response = new ApiResponse<>();
        response.setMessage("Tạo sản phẩm thành công");
        response.setResult(sanPhamService.create(id, sanPham));
        return response;
    }

    @PutMapping("/cuahang-{id}/{idsp}")
    public ApiResponse<SanPham> update(@PathVariable Integer idsp, @RequestBody SanPham sanPham) {
        ApiResponse<SanPham> response = new ApiResponse<>();
        response.setMessage("Cập nhật sản phẩm thành công");
        response.setResult(sanPhamService.update(sanPham));
        return response;
    }

    @DeleteMapping("/cuahang-{id}/{idsp}")
    public ResponseEntity<ApiResponse<Void>> deleteSanPham(@PathVariable int idsp) {
        sanPhamService.deleteById(idsp);
        ApiResponse<Void> response = new ApiResponse<>();
        response.setMessage("Đã xóa thành công");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cuahang-{id}/{idsp}")
    public SanPham get(@PathVariable Integer idsp) {
        return sanPhamService.findById(idsp);
    }

    @GetMapping("/cuahang-{id}/tim-kiem/tensanpham")
    public List<SanPhamView> SearchSanPhamByTenSanPham(@PathVariable int id, @RequestParam String ten){
        if (!sanPhamDao.existBySanPham(id, ten)) {
            throw new RuntimeException("Product not found");
        }
        return sanPhamService.findByTenSanPham(id, ten);
    }

    @GetMapping("/cuahang-{id}/tim-kiem/theloai")
    public List<SanPhamView> SearchSanPhamByTheLoai(@PathVariable int id, @RequestParam int category){
        return sanPhamService.findByTheLoai(id, category);
    }

    @GetMapping("/cuahang-{id}/tim-kiem/ngay-tao")
    public ResponseEntity<List<SanPhamView>> searchCreateDate(@PathVariable int id,
                                                          @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        List<SanPhamView> sanPhams = sanPhamService.findByCreateDate(id, date);
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

    //Xuat file excel
    @GetMapping("/cuahang-{id}/save/sanpham/excel")
    public ResponseEntity<ApiResponse<String>> saveFile(@PathVariable int id) {
        String file = "D:\\duanCaNhan\\5new\\Booker\\sanpham.xlsx";
        saveFileExcelService.saveSanPhamExcel(id, file);
        ApiResponse<String> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setResult("Lưu file thành công");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
