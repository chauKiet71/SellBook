package com.example.booker.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "san_pham_view")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamView {

    @Id
    Integer ma_san_pham;
    String ten_san_pham;
    String anh_san_pham;
    String tac_gia;
    String ten_the_loai;
    Integer ma_the_loai;
    Float gia;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate ngay_tao;
    Integer da_ban;
    Float doanh_thu;
    Float danh_gia;
    Integer so_luong_hang;
    Integer ma_cua_hang;

}
