package com.example.booker.dao;


import com.example.booker.entity.view.SanPhamView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SanPhamViewDao extends JpaRepository<SanPhamView, Integer> {

}


