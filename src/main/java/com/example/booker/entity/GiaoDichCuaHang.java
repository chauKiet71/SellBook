package com.example.booker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "giao_dich_cua_hang")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GiaoDichCuaHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_gd;
    private String mo_ta;
    private Float so_tien;
    private int ma_cua_hang;
    private String anh_qr;
    private int trang_thai;
    //0: chờ xác nhận
    //1: xác nhận
    //2: đã xong

    @ManyToOne
    @JoinColumn(name = "ma_cua_hang", insertable = false, updatable = false)
    CuaHang cua_hang;
}
