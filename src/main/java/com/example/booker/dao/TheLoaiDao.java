package com.example.booker.dao;

import com.example.booker.entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TheLoaiDao extends JpaRepository<TheLoai, Integer> {

    @Query("SELECT (COUNT(t) > 0) FROM TheLoai t WHERE t.ten_the_loai = ?1")
    boolean existsByTenTheLoai(String tenTheLoai);
}
