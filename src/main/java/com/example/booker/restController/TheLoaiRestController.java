package com.example.booker.restController;

import com.example.booker.dao.TheLoaiDao;
import com.example.booker.entity.TheLoai;
import com.example.booker.request.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin("*")
public class TheLoaiRestController {

    @Autowired
    TheLoaiDao tlDao;

    @GetMapping()
    public List<TheLoai> list() {
        return tlDao.findAll();
    }

    @GetMapping("{id}")
    public ApiResponse<TheLoai> get(@PathVariable int id) {
        ApiResponse<TheLoai> response = new ApiResponse<>();
        response.setResult(tlDao.findById(id).orElseThrow(() -> new RuntimeException("Category not found")));
        return response;
    }

    @PostMapping()
    public ApiResponse<TheLoai> create(@RequestBody @Valid TheLoai theLoais) {
        ApiResponse<TheLoai> response = new ApiResponse<>();
        if (tlDao.existsByTenTheLoai(theLoais.getTen_the_loai()))
            throw new RuntimeException("CATEGORY EXISTS");
        response.setResult(tlDao.save(theLoais));
        return response;
    }

//    @PostMapping()
//    public TheLoais add(@RequestBody TheLoais theLoais) {
//        return tlDao.save(theLoais);
//    }
}
