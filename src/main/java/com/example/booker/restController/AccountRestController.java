package com.example.booker.restController;

import com.example.booker.dao.TaiKhoanDao;
import com.example.booker.entity.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AccountRestController {

    @Autowired
    TaiKhoanDao tkDao;

    @GetMapping("/list")
    public List<TaiKhoan> list() {
        return tkDao.findAll();
    }

    @PostMapping()
    public TaiKhoan create(@RequestBody TaiKhoan tk) {
        return tkDao.save(tk);
    }

}
