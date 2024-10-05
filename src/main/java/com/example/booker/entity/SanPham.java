package com.example.booker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "san_pham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ma_san_pham;
    String ten_san_pham;
    String mo_ta;
    int so_luong_hang;
    float gia;
    LocalDate ngay_tao = LocalDate.now();
    int ma_the_loai;
//    int ma_cua_hang;

    @ManyToOne
    @JoinColumn(name = "ma_the_loai", insertable=false, updatable=false)
    TheLoai the_loai;

//    @ManyToOne
//    @JoinColumn(name = "ma_cua_hang")
//    @JsonIgnore
//    CuaHangs cua_hang;
}
