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
    int id_tai_khoan;
    String anh_dai_dien;
    String anh_bia;
    String email;
    Integer so_dien_thoai;
    Float diem_cua_hang;
    int tong_luot_ban;
    int luot_bao_cao;
    Boolean trang_thai_khoa = Boolean.FALSE;

    @JsonIgnore
    @OneToMany(mappedBy = "cua_hang")
    List<SanPham> sanPhamList;

    @JsonIgnore
    @OneToMany(mappedBy = "cua_hang")
    List<Voucher> voucherList;

}
