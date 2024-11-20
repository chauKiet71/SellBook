package com.example.booker.restController;
import com.example.booker.dao.BaoCaoCuaHangDao;
import com.example.booker.entity.BaoCaoCuaHang;
import com.example.booker.entity.TheLoai;
import com.example.booker.request.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/baocaocuahang")
@CrossOrigin("*")
public class BaoCaoCuaHangController {
    @Autowired
    BaoCaoCuaHangDao baoCaoCuaHangDao;

    @GetMapping
    public List<BaoCaoCuaHang> getAll() {
        return baoCaoCuaHangDao.findAll();
    }

    // lấy báo cáo theo idBao cáo
    @GetMapping("/{id}")
    public ApiResponse<BaoCaoCuaHang> getIDBaocao(@PathVariable int id) {
        ApiResponse<BaoCaoCuaHang> response = new ApiResponse<>();
        response.setResult(baoCaoCuaHangDao.findById(id).orElseThrow(() -> new RuntimeException("Category not found")));
        return response;
    }

    // get theo id_tai_khoan_bi_bao_cao báo cáo cho người dùng(thông báo các lỗi cửa tài khoản đã duyệt và chưa ẩn)
    @GetMapping("/thongbao-taikhoan/{id_tai_khoan}")
    public List<BaoCaoCuaHang> getThongbaosIdTK(@PathVariable int id_tai_khoan) {

        // Lấy danh sách báo cáo từ Repository
        List<BaoCaoCuaHang> thongBaos = baoCaoCuaHangDao.findThongbaochotaikhoan(id_tai_khoan);
        // Kiểm tra nếu không có báo cáo
        if (thongBaos.isEmpty()) {
            throw new RuntimeException("Không có thông báo nào cho tài khoản với ID: " + id_tai_khoan);
        }
        return thongBaos;
    }
    @PostMapping("/save")
    public ApiResponse<BaoCaoCuaHang> createBaoCaoCuaHang(@RequestBody BaoCaoCuaHang baoCao) {
        ApiResponse<BaoCaoCuaHang> response = new ApiResponse<>();
        try {
            baoCao.setTrang_thai_bao_cao(1);
            response.setMessage("Báo cáo cửa hàng đã được tạo thành công.");
            response.setResult(baoCaoCuaHangDao.save(baoCao));
        } catch (Exception e) {
            // Xử lý lỗi

            response.setMessage("Đã xảy ra lỗi khi tạo báo cáo cửa hàng: " + e.getMessage());
        }

        return response;
    }
    @PutMapping("/update")
    public BaoCaoCuaHang update(@RequestBody BaoCaoCuaHang baoCaos) {
        return baoCaoCuaHangDao.save(baoCaos);
    }
    //duyệt báo cáo
    @PutMapping("/update/update_baocao_duyet/{id}")
    public ApiResponse<BaoCaoCuaHang> duyetBaoCao(@PathVariable int id) {
        ApiResponse<BaoCaoCuaHang> response = new ApiResponse<>();

        try {
            BaoCaoCuaHang  baoCao = baoCaoCuaHangDao.findById(id).get();
            if (baoCao != null) {

                baoCao.setTrang_thai_bao_cao(2);
                response.setMessage("Báo cáo đã được duyet thành công.");
                response.setResult(baoCaoCuaHangDao.save(baoCao));
            }
        } catch (Exception e) {
             response.setMessage("Đã xảy ra lỗi khi " + e.getMessage());
        }

        return response;
    }
    @PutMapping("/update/update_baocao_daxem/{id}")
    public ApiResponse<BaoCaoCuaHang> xemBaoCao(@PathVariable int id) {
        ApiResponse<BaoCaoCuaHang> response = new ApiResponse<>();

        try {
            BaoCaoCuaHang  baoCao = baoCaoCuaHangDao.findById(id).get();
            if (baoCao != null) {

                baoCao.setTrang_thai_bao_cao(3);
                response.setMessage("Báo cáo đã được xem .");
                response.setResult(baoCaoCuaHangDao.save(baoCao));
            }
        } catch (Exception e) {
            response.setMessage("Đã xảy ra lỗi khi " + e.getMessage());
        }

        return response;
    }
    @PutMapping("/update/update_baocao_an/{id}")
    public ApiResponse<BaoCaoCuaHang> anBaoCao(@PathVariable int id) {
        ApiResponse<BaoCaoCuaHang> response = new ApiResponse<>();

        try {
            BaoCaoCuaHang  baoCao = baoCaoCuaHangDao.findById(id).get();
            if (baoCao != null) {

                baoCao.setTrang_thai_bao_cao(4);
                response.setMessage("Báo cáo đã được ẩn thành công.");
                response.setResult(baoCaoCuaHangDao.save(baoCao));
            }
        } catch (Exception e) {
            response.setMessage("Đã xảy ra lỗi khi " + e.getMessage());
        }

        return response;
    }
}
