package com.example.booker.dao;

import com.example.booker.entity.view.CuaHangView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CuaHangViewDao extends JpaRepository<CuaHangView, Integer> {

    @Query("SELECT c FROM CuaHangView c WHERE c.ma_cua_hang = :ma_cua_hang")
    CuaHangView getCuaHangView(int ma_cua_hang);

}


