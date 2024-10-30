package com.example.booker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    int so_luong = 1;
    float gia;
    float thanh_tien;

    @OneToOne
    @JoinColumn(name = "ma_san_pham", insertable = false, updatable = false)
    SanPham san_pham;

    @ManyToOne
    @JoinColumn(name = "ma_don_hang", insertable = false, updatable = false)
    DonHang don_hang;

    String ma_voucher;

    @OneToOne
    @JoinColumn(name = "ma_trang_thai", insertable = false, updatable = false)
    TrangThaiDonHang trang_thai;


}
