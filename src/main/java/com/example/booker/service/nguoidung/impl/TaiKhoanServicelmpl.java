package com.example.booker.service.nguoidung.impl;

import com.example.booker.dao.TaiKhoanDao;
import com.example.booker.entity.TaiKhoan;
import com.example.booker.service.nguoidung.TaiKhoanService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanServicelmpl implements TaiKhoanService {
    @Autowired
    TaiKhoanDao taiKhoanDao;

    @Override
    public List<TaiKhoan> getTaiKhoans() {
        List<TaiKhoan> taiKhoans = taiKhoanDao.findAll();
        return taiKhoans;
    }



    @Override
    public TaiKhoan getTaiKhoanById(int id) {
        return taiKhoanDao.findById(id).orElse(null);
    }
    @Override
    public TaiKhoan getByEmail(String email) {
        return taiKhoanDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại với email: " + email));

    }

    @Override
    public TaiKhoan saveTaikhoan(TaiKhoan taiKhoan){
           if(taiKhoanDao.existsByEmail(taiKhoan.getEmail())){
               throw new RuntimeException("Email tài khoản đã tồn taị");
           }
           return taiKhoanDao.save(taiKhoan);
    }
    @Override
    public TaiKhoan updateTaikhoan(int id, TaiKhoan taiKhoan){
        return taiKhoanDao.save(taiKhoan);
    }

    @Override
    public TaiKhoan vohieuhoa_khachhang(int id, TaiKhoan taiKhoan){
        TaiKhoan existingTaiKhoan = taiKhoanDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));
         existingTaiKhoan.setTrang_thai_tk(true);
       // Lưu lại đối tượng đã cập nhật
        return taiKhoanDao.save(existingTaiKhoan);

    }


    @Override
    public void deleteById(int id){
        try {
            taiKhoanDao.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("not tk id: " + id);
        }
    }


}
