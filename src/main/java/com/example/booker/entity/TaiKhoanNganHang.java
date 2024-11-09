package com.example.booker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tai_khoan_ngan_hang")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaiKhoanNganHang {

    @Id
    private String so_tai_khoan;
    private String chu_tai_khoan;
    private String ten_ngan_hang;
    private int id_tai_khoan;
    private int ma_cua_hang;

//    @ManyToOne
//    @JoinColumn(name = "ma_cua_hang", insertable = false, updatable = false)
//    private CuaHang cua_hang;
}
