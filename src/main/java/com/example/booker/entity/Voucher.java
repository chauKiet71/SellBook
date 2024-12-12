package com.example.booker.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_voucher;
    String ten_voucher;
    Float giam_gia;
    LocalDate ngay_bat_dau = LocalDate.now();
    LocalDate ngay_het_han;
    Float gia_ap_dung;
    int ma_cua_hang;
    int so_lan_dung;
    String dieu_kien;

    @Column(name = "trang_thai")
    private Byte trangThai; //0 - chưa duyệt , 1 - hết hạn,  2 - còn hạng

    @ManyToOne
    @JoinColumn(name = "ma_cua_hang", insertable = false, updatable = false)
    CuaHang cua_hang;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher")
    List<DonHangChiTiet> donHangChiTiets;
    // Getter và setter cho các thuộc tính khác

    // Getter cho thuộc tính trangThai
//    public String gettrang_thai_text() {
//        LocalDate today = LocalDate.now();
//
//        if (today.isBefore(ngay_bat_dau)) {
//            return "chưa áp dụng";
//        } else if (today.isAfter(ngay_bat_dau)) {
//            return "hết hạn";
//        } else {
//            long daysLeft = ChronoUnit.DAYS.between(today, ngay_het_han);
//            return "còn " + daysLeft + " ngày";
//        }
//    }
//
//    public Integer gettrang_thai_int() {
//        LocalDate today = LocalDate.now();
//        if (today.isBefore(ngay_bat_dau)) {
//            return 0;
//        }else if (today.isAfter(ngay_bat_dau)) {
//            return 1;
//        }else {
//            return 2;
//        }
//    }
}
