package com.example.booker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
import java.util.Date;
=======
import java.time.LocalDate;
>>>>>>> 589e5b8786da79103f14e9320252efd051f26fb9
import java.util.List;

@Entity
@Table(name = "don_hang")
<<<<<<< HEAD
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
    @JoinColumn(name = "id_tai_khoan", insertable = false, updatable = false)
    TaiKhoan tai_khoan;

    @JsonIgnore
    @OneToMany(mappedBy = "donHang")
    List<DonHangChiTiet> don_hang_chi_tiet;

    @ManyToOne
    @JoinColumn(name = "ma_dia_chi", insertable = false, updatable = false)
    DiaChi dia_chi;
=======
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

    @JsonIgnore
    @OneToMany(mappedBy = "don_hang")
    List<DonHangChiTiet> donHangChiTiets;

>>>>>>> 589e5b8786da79103f14e9320252efd051f26fb9
}
