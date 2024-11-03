package com.example.booker.restController;


import com.example.booker.entity.CuaHang;
import com.example.booker.service.nguoidung.CuaHangService;
import com.example.booker.request.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cuahang")
public class CuaHangRestController {

    @Autowired
    CuaHangService cuaHangService;

    @GetMapping()
    public List<CuaHang> getCuaHang(){
        return cuaHangService.getAllCuaHang();
    }

    @PostMapping()
    public ApiResponse<CuaHang> addCuaHang(@RequestBody CuaHang cuaHang){
        ApiResponse<CuaHang> response = new ApiResponse<>();
        response.setResult(cuaHangService.createCuaHang(cuaHang));
        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<CuaHang> updateCuaHang(@RequestBody CuaHang cuaHang, @PathVariable int id){
        ApiResponse<CuaHang> response = new ApiResponse<>();
        response.setMessage("Cập nhật cửa hàng thành công");
        response.setResult(cuaHangService.updateCuaHang(cuaHang));
        return response;
    }

    @PutMapping("/khoa/{id}")
    public ApiResponse<CuaHang> updateKhoaCuaHang(@RequestBody CuaHang cuaHang, @PathVariable int id){
        ApiResponse<CuaHang> response = new ApiResponse<>();
        response.setMessage("Khóa cửa hàng thành công");
        response.setResult(cuaHangService.khoaCuaHang(id,cuaHang));
        return response;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCuaHang(@PathVariable int id){
        cuaHangService.deleteCuaHang(id);
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Xoá cửa hàng thành công");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<CuaHang> getCuaHangById(@PathVariable int id){
        ApiResponse<CuaHang> response = new ApiResponse<>();
        response.setResult(cuaHangService.getCuaHangById(id));
        return response;
    }

}
