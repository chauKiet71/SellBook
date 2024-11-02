package com.example.booker.restController;

import com.example.booker.entity.TaiKhoan;
import com.example.booker.request.ApiResponse;
import com.example.booker.service.nguoidung.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taikhoan")
@CrossOrigin("*")
public class TaiKhoanRestController {
    @Autowired
    TaiKhoanService tkService;

    @GetMapping
    public List<TaiKhoan> getAll() {
        return tkService.getTaiKhoans();
    }
    @GetMapping("/email/{email}")
    public ApiResponse<TaiKhoan> getByEmail(@PathVariable String email) {
        ApiResponse<TaiKhoan> response = new ApiResponse<>();

        response.setMessage("Thành công");
        response.setResult(tkService.getByEmail(email));
        return response;

    }
    @GetMapping("/{id}")
    public ApiResponse<TaiKhoan> getById(@PathVariable int id) {
        ApiResponse<TaiKhoan> response = new ApiResponse<>();
        response.setResult(tkService.getTaiKhoanById(id));
        return response;
    }

    @PostMapping
    public ApiResponse<TaiKhoan> create(@RequestBody TaiKhoan taiKhoan) {
        ApiResponse<TaiKhoan> response = new ApiResponse<>();
        response.setResult(tkService.saveTaikhoan(taiKhoan));
        response.setMessage("Tạo tài khoản thành công");
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTK(@PathVariable int id) {
        tkService.deleteById(id);
        ApiResponse<Void> response = new ApiResponse<>();
        response.setMessage("Đã xóa thành công");
        return ResponseEntity.ok(response);
    }
    @PutMapping("/upatate/{id}")
    public ApiResponse<TaiKhoan> update(@PathVariable int id, @RequestBody TaiKhoan taiKhoan) {
        ApiResponse<TaiKhoan> response = new ApiResponse<>();
        response.setMessage("Thành công");
        response.setResult(tkService.updateTaikhoan(id,taiKhoan));
        return response;
    }

    @PutMapping("/upatate/trangthai/{id}")
    public ApiResponse<TaiKhoan> update_trangthai(@PathVariable int id, @RequestBody TaiKhoan taiKhoan) {
        ApiResponse<TaiKhoan> response = new ApiResponse<>();
        response.setMessage("Thành công");
        response.setResult(tkService.vohieuhoa_khachhang(id,taiKhoan));

        return response;
    }
}
