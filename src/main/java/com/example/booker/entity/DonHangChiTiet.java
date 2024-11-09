package com.example.booker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    @ManyToOne
    @JoinColumn(name = "ma_san_pham", insertable = false, updatable = false)
    SanPham san_pham;

    @ManyToOne
    @JoinColumn(name = "ma_don_hang", insertable = false, updatable = false)
    DonHang don_hang;

    @ManyToOne
    @JoinColumn(name = "ma_trang_thai")
    TrangThaiDonHang trang_thai;

    @ManyToOne
    @JoinColumn(name = "id_voucher",  insertable=false, updatable=false)
    Voucher voucher;

//    public DonHangChiTiet(String ten_san_pham, int so_luong, float thanh_tien) {
//        this.san_pham.ten_san_pham = ten_san_pham;
//        this.so_luong = so_luong;
//        this.thanh_tien = thanh_tien;
//    }


}
