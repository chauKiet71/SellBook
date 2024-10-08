package com.example.booker.dto.request;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDto {

    int ma_san_pham;
    String ten_san_pham;
    String mo_ta;
    int so_luong_hang;
    float gia;
    LocalDate ngay_tao = LocalDate.now();
    int ma_the_loai;
    int tong_so_luong;

}
