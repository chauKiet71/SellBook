package com.example.booker.restController;

import com.example.booker.dao.DanhGiaDao;
import com.example.booker.entity.DanhGia;
import com.example.booker.service.nguoidung.DanhGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/danhgia")
public class DanhGiaRestController {

    @Autowired
    private DanhGiaService danhGiaService;

    @Autowired
    private DanhGiaDao danhGiaDao;

    @GetMapping("/ma_san_pham-{id}")
    public List<DanhGia> getDanhGia(@PathVariable int id) {
        return danhGiaService.getDanhGiaList(id);
    }

    @GetMapping("/tat-ca-danh-gia/ma_san_pham-{id}")
    public Long getCountDanhGia(@PathVariable int id) {
        return danhGiaDao.countByMaSanPham(id);
    }

    @GetMapping("/tim-kiem/ma_san_pham-{idSp}/diem_danh_gia-{diem}")
    public List<DanhGia> searchDanhGia(@PathVariable int idSp, @PathVariable int diem) {
        return danhGiaDao.findByMaSanPhamAndDiemDanhGia(idSp, diem);
    }

}
