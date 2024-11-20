package com.example.booker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "don_hang")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ma_don_hang;
    @Temporal(TemporalType.DATE)
    Date ngay_tao;

    @ManyToOne
    @JoinColumn(name = "id_tai_khoan")
    TaiKhoan tai_khoan;

    @ManyToOne
    @JoinColumn(name = "ma_dia_chi")
    DiaChi dia_chi;



    @JsonIgnore
    @OneToMany(mappedBy = "don_hang")
    List<DonHangChiTiet> donHangChiTiets;

    @JsonIgnore
    @OneToMany(mappedBy = "donHang")
    List<HuyDonHang> huyDonHangs;
}
