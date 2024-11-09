package com.example.booker.restController;

import com.example.booker.dao.BaoCaoDao;
import com.example.booker.entity.BaoCao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/baocao")
@CrossOrigin("*")
public class BaoCaoRestController {
    @Autowired
    BaoCaoDao baoCaoDao;

    @GetMapping
    public List<BaoCao> getAll() {return baoCaoDao.findAll();}

    @GetMapping("/ND/{id}")
    public List<BaoCao> getById(@PathVariable int id) {
        return baoCaoDao.findThongBaoNguoiDungById(id);
    }
}
