package com.example.booker.restController;

import com.example.booker.dao.SanPhamDao;
import com.example.booker.dao.TheLoaiDao;
import com.example.booker.entity.TheLoai;
import com.example.booker.request.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin("*")
public class TheLoaiRestController {

    @Autowired
    TheLoaiDao tlDao;
    @Autowired
    SanPhamDao sanPhamDao;
    @Autowired
    private TheLoaiDao theLoaiDao;

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

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        ApiResponse<Void> response = new ApiResponse<>();

        try {
            // Kiểm tra xem thể loại có tồn tại trong CSDL không
            Optional<TheLoai> existing = tlDao.findById(id);
            if (existing.isPresent()) {
                tlDao.deleteById(id); // Xóa thể loại dựa trên id
                response.setMessage("Xóa thể loại thành công");
            } else {
                response.setMessage("Không tìm thấy ID thể loại.");
            }
        } catch (DataIntegrityViolationException e) {
            response.setMessage("Không thể xóa thể loại vì nó đang được liên kết với các sản phẩm.");
        } catch (Exception e) {
            response.setMessage("Có lỗi xảy ra khi xóa thể loại.");
        }

        return response;
    }



    //    @PostMapping()
//    public TheLoais add(@RequestBody TheLoais theLoais) {
//        return tlDao.save(theLoais);
//    }
}
