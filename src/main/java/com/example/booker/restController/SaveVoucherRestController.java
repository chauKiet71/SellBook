package com.example.booker.restController;

import com.example.booker.dao.SaveVoucherDao;
import com.example.booker.entity.SaveVoucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/save-voucher")
public class SaveVoucherRestController {

    @Autowired
    SaveVoucherDao saveVoucherDao;

    @PostMapping()
    public SaveVoucher saveVoucher(@RequestBody SaveVoucher saveVoucher) {
        return saveVoucherDao.save(saveVoucher);
    }

    @GetMapping("/get-{id_tk}")
    public List<SaveVoucher> getAllVouchers(@PathVariable int id_tk) {
        return saveVoucherDao.findVoucherByIdtk(id_tk);
    }

    @GetMapping("/{id_tk}/{ma_cua_hang}")
    public List<SaveVoucher> getVoucher(@PathVariable int ma_cua_hang, @PathVariable int id_tk) {
        return saveVoucherDao.findVoucherByMaCuaHang(ma_cua_hang, id_tk);
    }
}
