package com.example.booker.restController;


import com.example.booker.dao.CuaHangDao;
import com.example.booker.entity.CuaHang;
import com.example.booker.service.nguoidung.CuaHangService;
import com.example.booker.request.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cuahang")
public class CuaHangRestController {

    @Autowired
    CuaHangService cuaHangService;

    @Autowired
    CuaHangDao cuaHangDao;

    @GetMapping()
    public List<CuaHang> getCuaHang() {
        return cuaHangService.getAllCuaHang();
    }

    @PostMapping()
    public ApiResponse<CuaHang> addCuaHang(@RequestBody CuaHang cuaHang) {
        ApiResponse<CuaHang> response = new ApiResponse<>();
        response.setResult(cuaHangService.createCuaHang(cuaHang));
        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<CuaHang> updateCuaHang(@RequestBody CuaHang cuaHang, @PathVariable int id) {
        ApiResponse<CuaHang> response = new ApiResponse<>();
        response.setMessage("Cập nhật cửa hàng thành công");
        response.setResult(cuaHangService.updateCuaHang(cuaHang));
        return response;
    }

//    @PutMapping("/khoa/{id}")
//    public ApiResponse<CuaHang> updateKhoaCuaHang(@RequestBody CuaHang cuaHang, @PathVariable int id){
//        ApiResponse<CuaHang> response = new ApiResponse<>();
//        response.setMessage("Khóa cửa hàng thành công");
//        response.setResult(cuaHangService.khoaCuaHang(id,cuaHang));
//        return response;
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCuaHang(@PathVariable int id) {
        cuaHangService.deleteCuaHang(id);
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Xoá cửa hàng thành công");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<CuaHang> getCuaHangById(@PathVariable int id) {
        ApiResponse<CuaHang> response = new ApiResponse<>();
        response.setResult(cuaHangService.getCuaHangById(id));
        return response;
    }

//    ADMIN - lấy cửa hàng theo mã trạng thái cửa hàng
    @GetMapping("/trang_thai/{id}")
    public List<CuaHang> getCuaHangByDuyet(@PathVariable("id") Integer id) {
        return cuaHangDao.getCuaHangByTrangThai(id);
    }

    // thong tin cua hang trong chi tiet san pham
    @GetMapping("/info/{maCuaHang}")
    public ResponseEntity<?> getCuaHangInfo(@PathVariable("maCuaHang") int maCuaHang) {
        try {
            // Lấy thông tin cửa hàng
            CuaHang cuaHang = cuaHangService.getCuaHangById(maCuaHang);
            if (cuaHang == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy cửa hàng");
            }

            // Trả về dữ liệu cần thiết
            Map<String, Object> response = new HashMap<>();
            response.put("ma_cua_hang", cuaHang.getMa_cua_hang());
            response.put("ten_cua_hang", cuaHang.getTen_cua_hang());
            response.put("dia_chi_cua_hang", cuaHang.getDia_chi_cua_hang());
            response.put("anh_dai_dien", cuaHang.getAnh_dai_dien());
            response.put("anh_bia", cuaHang.getAnh_bia());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi lấy thông tin cửa hàng: " + e.getMessage());
        }
    }





}






















