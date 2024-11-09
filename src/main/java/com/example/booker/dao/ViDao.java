package com.example.booker.dao;

import com.example.booker.entity.Vi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViDao extends JpaRepository<Vi, Integer> {

    @Query("SELECT v FROM Vi v WHERE v.id_tai_khoan = :id_tk")
    Vi findByTaiKhoan(int id_tk);
}
