package com.example.booker.restController;

import com.example.booker.dao.SanPhamDao;
import com.example.booker.entity.SanPham;
import com.example.booker.entity.SanPham;
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

    @Autowired
    private SanPhamDao sanPhamViewDao;

    @GetMapping("/allinfo")
    public List<SanPham> getAllSanPham(){
        return sanPhamDao.findAll();
    }

    @GetMapping("/cuahang-{id}")
    public List<SanPham> getSp(@PathVariable int id) {
        return sanPhamService.findAll(id);
    }

//    Lấy số lượng sản pham
    @GetMapping("/cuahang-{id}/count")
    public long countSanPhamAll(@PathVariable int id) {
        return sanPhamViewDao.countByMaCuaHang(id);
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
    //khoa san pham
    @PutMapping("/cuahang-{id}/khoasp/{idsp}")
    public ApiResponse<SanPham> update_khoasp(@PathVariable Integer idsp, @RequestBody SanPham sanPham) {
        ApiResponse<SanPham> response = new ApiResponse<>();
        response.setMessage(("Khóa sản phẩm thành công"));
        response.setResult(sanPhamService.khoa_sanpham(idsp, sanPham));
        return response;
    }
    //duyet san pham
    @PutMapping("/cuahang-{id}/duyet_sp/{idsp}")
    public ApiResponse<SanPham> update_duyet_sp(@PathVariable Integer idsp, @RequestBody SanPham sanPham) {
        ApiResponse<SanPham> response = new ApiResponse<>();
        response.setMessage(("duyet sản phẩm thành công"));
        response.setResult(sanPhamService.duyet_sanpham(idsp, sanPham));
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
    public List<SanPham> SearchSanPhamByTenSanPham(@PathVariable int id, @RequestParam String ten) {
        if (!sanPhamDao.existBySanPham(id, ten)) {
            throw new RuntimeException("Product not found");
        }
        return sanPhamService.findByTenSanPham(id, ten);
    }

    @GetMapping("/cuahang-{id}/tim-kiem/theloai")
    public List<SanPham> SearchSanPhamByTheLoai(@PathVariable int id, @RequestParam int category) {
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

    @GetMapping("/cuahang-{id}/spbikhoa")
    public List<SanPham> getSpBiKhoa(@PathVariable int id) {
        return sanPhamService.sanPhamByTrangThaiKhoa(id);
    }

    @GetMapping("/cuahang-{id}/choduyet")
    public List<SanPham> getSpChoDuyet(@PathVariable int id) {
        return sanPhamService.sanPhamByChoDuyet(id);
    }

    @GetMapping("/cuahang-{id}/hethang")
    public List<SanPham> getSpHetHang(@PathVariable int id) {
        return sanPhamService.sanPhamByHetHang(id);
    }

    @GetMapping("/cuahang-{id}/conhang")
    public List<SanPham> getSpConHang(@PathVariable int id) {
        return sanPhamService.sanPhamByConHang(id);
    }

    @GetMapping("/cuahang-{id}/tim-kiem/trangthai/{matt}")
    public List<SanPham> searchSanPhamTrangThai(@PathVariable int id, @PathVariable int matt) {
        return sanPhamService.searchSanPhamByTrangThai(id, matt);
    }

    //lay san pham theo luot ban tu cao den thap
    @GetMapping("/cuahang-{id}/desc")
    public List<SanPham> findAllSanPhamByDabanDesc(@PathVariable int id) {
        return sanPhamService.findAllSanPhamByLuotBan(id);
    }

    //lấy ra sản phẩm bán chạy 7 ngày
//    @GetMapping("/cuahang-{id}/sp-7ngay")
//    public List<SanPham> sanPham7Ngay(@PathVariable int id) {
//        return sanPhamService.sanPham7Day(id);
//    }

    @GetMapping("/cuahang-{id}/sap-xep/diemdanhgia")
    public List<SanPham> getSanPhamOrderByComment(@PathVariable int id) {
        return sanPhamDao.getListProductOrderByComment(id);
    }

//    lấy danh sách sản phẩm sắp xếp theo bán bán theo
    @GetMapping("/cuahang-{id}/ban-chay")
    public List<SanPham> getSanPhamByBanChay(@PathVariable int id) {
        return sanPhamDao.getListProductOrderByDaBanDesc(id);
    }

    //    Lấy danh sách sản phẩm sắp xê theo doanh thu
    @GetMapping("/cuahang-{id}/doanh-thu")
    public List<SanPham> getSanPhamByDoanhThu(@PathVariable int id) {
        return sanPhamDao.getListProductOrderByDoanhThuDesc(id);
    }

//    ADMIN - lấy sản phẩm chờ duyê
    @GetMapping("/cho-duyet")
    public List<SanPham> getConHang(){
        return sanPhamDao.getListProductConHang();
    }
    //    ADMIN - lấy sản phẩm bị khóa
    @GetMapping("/vo-hieu-hoa")
    public List<SanPham> getKhoa(){
        return sanPhamDao.getListProductKhoa();
    }
    //    ADMIN - lấy sản phẩm còn hàng  va hết hangf
    @GetMapping("/dang-ban")
    public List<SanPham> DangBan(){
        return sanPhamDao.getListBookDangBan();
    }
}
