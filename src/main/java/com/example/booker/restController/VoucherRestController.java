package com.example.booker.restController;

import com.example.booker.entity.Voucher;
import com.example.booker.service.nguoidung.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @DeleteMapping("/cuahang-{id}/{idch}")
    public void deleteVoucher(@PathVariable int id, @PathVariable String ma_voucher) {
        voucherService.deleteVoucher(id, ma_voucher);
    }

    @GetMapping("/cuahang-{id}/{ma_voucher}")
    public Voucher getVoucher(@PathVariable int id, @PathVariable String ma_voucher) {
        return voucherService.getVoucherByMaVoucher(id, ma_voucher);
    }
}
