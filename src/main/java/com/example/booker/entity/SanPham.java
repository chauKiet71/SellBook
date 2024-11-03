package com.example.booker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

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

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate ngay_tao = LocalDate.now();
    int ma_the_loai;
    String tac_gia;
    LocalDate ngay_xuat_ban;
    int so_trang;
    Float ma_isbn;
    String phien_ban;
    String anh_san_pham;
    int ma_cua_hang;
    Boolean trang_thai_duyet = Boolean.FALSE;
    Boolean trang_thai_khoa = Boolean.FALSE;
    int da_ban;
    Float diem_trung_binh;
    Float doanh_thu;
    int con_hang;

    @Column(name = "trang_thai_hoat_dong", insertable = false, updatable = false)

    private Byte trang_thai_hoat_dong;

    @ManyToOne
    @JoinColumn(name = "ma_the_loai", insertable = false, updatable = false)
    TheLoai the_loai;

    @ManyToOne
    @JoinColumn(name = "ma_cua_hang", insertable=false, updatable=false)
    CuaHang cua_hang;

    @JsonIgnore
    @OneToMany(mappedBy = "san_pham")
    List<DonHangChiTiet> donHangChiTiets;
}
