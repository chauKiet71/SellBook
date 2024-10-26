package com.example.booker.restController;

import com.example.booker.entity.Voucher;
import com.example.booker.request.ApiResponse;
import com.example.booker.service.nguoidung.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/voucher")
public class VoucherRestController {

    @Autowired
    VoucherService voucherService;

    @GetMapping("/cuahang-{id}")
    public List<Voucher> cuahang(@PathVariable int id) {
        return voucherService.getVouchers(id);
    }

    @PostMapping("/cuahang-{id}")
    public Voucher addVoucher(@RequestBody Voucher voucher) {
        return voucherService.createVoucher(voucher);
    }

    @PutMapping("/cuahang-{id}/{ma_voucher}")
    public Voucher updateVoucher(@RequestBody Voucher voucher) {
        return voucherService.updateVoucher(voucher);
    }

    @DeleteMapping("/{id_voucher}")
    public ResponseEntity<ApiResponse<Void>> deleteVoucher(@PathVariable int id_voucher) {
        voucherService.deleteVoucher(id_voucher);
        ApiResponse<Void> response = new ApiResponse<>();
        response.setMessage("Xoá thành công");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cuahang-{id}/{ma_voucher}")
    public Voucher getVoucher(@PathVariable int id, @PathVariable int ma_voucher) {
        return voucherService.getVoucherByMaVoucher(id, ma_voucher);
    }
}
