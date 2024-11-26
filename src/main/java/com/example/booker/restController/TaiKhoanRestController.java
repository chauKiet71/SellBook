package com.example.booker.restController;

import com.example.booker.dao.TaiKhoanDao;
import com.example.booker.dao.ViDao;
import com.example.booker.entity.TaiKhoan;
import com.example.booker.entity.Vi;
import com.example.booker.request.ApiResponse;
import com.example.booker.service.nguoidung.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/taikhoan")
@CrossOrigin("*")
public class TaiKhoanRestController {
    @Autowired
    TaiKhoanService tkService;

    @Autowired
    TaiKhoanDao tkDao;
    @Autowired
    private ViDao viDao;

    @GetMapping
    public List<TaiKhoan> getAll() {
        return tkService.getTaiKhoans();
    }

//    ADMIN - lấy tài khoản khách hàng: user , seller
    @GetMapping("/customer-all")
    public List<TaiKhoan> getAllCustomer() {
        return tkDao.findAllCustomer();
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
    @PostMapping("/login")
    public ApiResponse<TaiKhoan> login(@RequestBody TaiKhoan loginRequest) {
        ApiResponse<TaiKhoan> response = new ApiResponse<>();

        // Sử dụng service để kiểm tra tài khoản
        TaiKhoan user = tkService.validateLogin(loginRequest.getEmail(), loginRequest.getMat_khau());

        if (user != null) {
            response.setMessage("Đăng nhập thành công");
            response.setResult(user);
        } else {
            response.setMessage("Email hoặc mật khẩu không chính xác");
            response.setResult(null);
        }

        return response;
    }
    @PostMapping("/register")
    public ApiResponse<TaiKhoan> create(@RequestBody TaiKhoan taiKhoan) {
        ApiResponse<TaiKhoan> response = new ApiResponse<>();
        response.setResult(tkService.saveTaikhoan(taiKhoan));
        System.out.println("id tk moi tao: " + taiKhoan.getId_tai_khoan());
        Vi newVi = new Vi();
        newVi.setId_vi("TTTSS" + taiKhoan.getId_tai_khoan());// tu set random
        newVi.setSo_tien(0.00F);
        newVi.setTai_khoan(taiKhoan);
        viDao.save(newVi);
        response.setMessage("Tạo tài khoản thành công");
        return response;
    }
    // API để lấy thông tin hồ sơ của người dùng
    @GetMapping("/profile/{id}")
    public ResponseEntity<ApiResponse<TaiKhoan>> getProfile(@PathVariable int id) {
        ApiResponse<TaiKhoan> response = new ApiResponse<>();
        TaiKhoan taiKhoan = tkService.getTaiKhoanById(id);
        if (taiKhoan != null) {
            response.setResult(taiKhoan);
            response.setMessage("Lấy thông tin hồ sơ thành công");
        } else {
            response.setMessage("Không tìm thấy tài khoản với id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    // API để cập nhật thông tin hồ sơ của người dùng
    @PutMapping("/profile/{id}")
    public ResponseEntity<ApiResponse<TaiKhoan>> updateProfile(
            @PathVariable int id,
            @RequestBody TaiKhoan profileData
    ) {
        ApiResponse<TaiKhoan> response = new ApiResponse<>();

        // Kiểm tra tài khoản có tồn tại không
        TaiKhoan existingTaiKhoan = tkService.getTaiKhoanById(id);
        if (existingTaiKhoan == null) {
            response.setMessage("Không tìm thấy tài khoản với id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Cập nhật thông tin hồ sơ
        existingTaiKhoan.setHo_ten(profileData.getHo_ten());
        existingTaiKhoan.setSo_dt(profileData.getSo_dt());
        existingTaiKhoan.setNgay_sinh(profileData.getNgay_sinh());

        // Lưu lại thông tin đã cập nhật
        TaiKhoan updatedTaiKhoan = tkService.updateTaikhoan(id, existingTaiKhoan);
        response.setResult(updatedTaiKhoan);
        response.setMessage("Cập nhật thông tin hồ sơ thành công");

        return ResponseEntity.ok(response);
    }
    @PostMapping("/change-password")
    public ApiResponse<String> changePassword(
            @RequestParam int idTaiKhoan,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword) {

        ApiResponse<String> response = new ApiResponse<>();

        // Kiểm tra xem mật khẩu mới và nhập lại mật khẩu có khớp nhau không
        if (!newPassword.equals(confirmPassword)) {
            response.setMessage("Mật khẩu mới và nhập lại mật khẩu không khớp");
            response.setCode(400); // Bad request
            return response;
        }

        try {
            // Sử dụng service để thay đổi mật khẩu
            boolean success = tkService.changePassword(idTaiKhoan, oldPassword, newPassword);

            if (success) {
                response.setMessage("Đổi mật khẩu thành công");
                response.setCode(200); // Success
            } else {
                response.setMessage("Mật khẩu cũ không đúng");
                response.setCode(400); // Bad request
            }
        } catch (Exception e) {
            response.setMessage("Đã xảy ra lỗi khi đổi mật khẩu");
            response.setCode(500); // Internal Server Error
        }

        return response;
    }

    //    ADMIN - lấy tài khoản vi phạm
    @GetMapping("/vi-pham")
    public List<TaiKhoan> getTaikhoavipham() {
        return tkDao.findtaikhoanvipham();
    }
}
