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
@Table(name = "TaiKhoan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tai_khoan;

    private String email;

    private String mat_khau;

    private String ho_ten;

    @Temporal(TemporalType.DATE)
    private Date ngay_sinh;

    private String so_dt;

    @Temporal(TemporalType.DATE)
    private Date ngay_tao;

    @JsonIgnore
    @OneToMany(mappedBy = "tai_khoan")
    List<DonHang> donHangs;

//    @ManyToOne
//    @JoinColumn(name = "ma_vai_tro", referencedColumnName = "ma_vai_tro")
//    private VaiTro ma_vai_tro;
    private Boolean trang_thai_tk = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "ma_vai_tro")
    private VaiTro ma_vai_tro;

    // Đặt giá trị ngày tạo mặc định là ngày hiện tại
    @PrePersist
    protected void onCreate() {
        this.ngay_tao = new Date();
    }


}
