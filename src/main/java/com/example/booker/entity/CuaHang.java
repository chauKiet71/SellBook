package com.example.booker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "cua_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)// cho tat ca cac thuoc tinh la private
public class CuaHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ma_cua_hang;
    String ten_cua_hang;
    String dia_chi_cua_hang;
    String anh_dai_dien;
    String anh_bia;
    String email;
    Integer so_dien_thoai;
    Float diem_cua_hang;
    int tong_diem_vi_pham;
    int tong_luot_ban;
    int luot_bao_cao;

    @ManyToOne
    @JoinColumn(name = "ma_trang_thai_cua_hang", insertable=false, updatable=false)
    TrangThaiCuaHang trang_thai_cua_hang;

    Boolean trang_thai_khoa = Boolean.FALSE;
    String so_tai_khoan;

    @ManyToOne
    @JoinColumn(name = "id_tai_khoan", insertable=false, updatable=false)
    TaiKhoan tai_khoan;

    @JsonIgnore
    @OneToMany(mappedBy = "cua_hang")
    List<SanPham> sanPhamList;

    @JsonIgnore
    @OneToMany(mappedBy = "cua_hang")
    List<Voucher> voucherList;

    @JsonIgnore
    @OneToMany(mappedBy = "cua_hang")
    List<GiaoDichCuaHang> giaoDichCuaHangList;

    @JsonIgnore
    @OneToMany(mappedBy = "cua_hang")
    List<TaiKhoanNganHang> tai_khoan_ngan_hang;
}
