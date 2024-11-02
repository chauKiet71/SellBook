package com.example.booker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "don_hang_chi_tiet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonHangChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ma_don_hang_chi_tiet;
    int so_luong;
    float gia;
    float thanh_tien;
    int ma_san_pham;
    int ma_don_hang;
    int id_voucher;
    int ma_trang_thai;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham", insertable=false, updatable=false)
    SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "ma_don_hang", insertable = false, updatable = false)
    DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "id_voucher", insertable = false, updatable = false)
    Voucher voucher;

}
