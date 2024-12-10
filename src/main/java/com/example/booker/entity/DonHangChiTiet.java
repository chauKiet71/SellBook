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
@Table(name = "don_hang_chi_tiet")
public class DonHangChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ma_don_hang_chi_tiet;
    int so_luong;
    float gia;
    float thanh_tien;
    float phi_dich_vu;
    float doanh_thu;
    boolean phuong_thuc_tt;


    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    SanPham san_pham;

    @ManyToOne
    @JoinColumn(name = "ma_don_hang")
    DonHang don_hang;

    @ManyToOne
    @JoinColumn(name = "ma_trang_thai")
    TrangThaiDonHang trang_thai;

    @ManyToOne
    @JoinColumn(name = "id_voucher")
    Voucher voucher;

}
