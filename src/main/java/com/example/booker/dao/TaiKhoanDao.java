package com.example.booker.dao;

import com.example.booker.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TaiKhoanDao extends JpaRepository<TaiKhoan, Integer> {
    // Phương thức kiểm tra sự tồn tại của tài khoản theo email
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TaiKhoan t WHERE t.email = :email")
    boolean existsByEmail(@Param("email") String email);
    Optional<TaiKhoan> findByEmail(String email);
}
