package com.example.booker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "danh_gia")
public class DanhGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ma_danh_gia;
    int diem_danh_gia;
    String noi_dung_danh_gia;
    LocalDate ngay_danh_gia = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "ma_don_hang_chi_tiet", insertable=false, updatable=false)
    DonHangChiTiet don_hang_chi_tiet;

    @ManyToOne
    @JoinColumn(name = "tai_khoan_danh_gia", insertable=false, updatable=false)
    TaiKhoan tai_khoan_danh_gia;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham", insertable=false, updatable=false)
    SanPham san_pham;
}