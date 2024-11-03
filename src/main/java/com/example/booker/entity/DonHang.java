package com.example.booker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "don_hang")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_don_hang;
    private LocalDate ngay_tao;
    private Integer id_tai_khoan;
    private Integer ma_dia_chi;

    @ManyToOne
    @JoinColumn(name = "id_tai_khoan", insertable=false, updatable=false )
    TaiKhoan tai_khoan;

    @ManyToOne
    @JoinColumn(name = "ma_dia_chi", insertable=false, updatable=false )
    DiaChi dia_chi;

    @JsonIgnore
    @OneToMany(mappedBy = "don_hang")
    List<DonHangChiTiet> donHangChiTiets;

}
