package com.example.booker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity @Table(name = "dia_chi")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DiaChi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_dia_chi;
    private String ten_dia_chi;

    @ManyToOne
    @JoinColumn(name = "id_tai_khoan", insertable=false, updatable=false )
    TaiKhoan tai_khoan;

    @JsonIgnore
    @OneToMany(mappedBy = "dia_chi")
    List<DonHang> donHangs;

}
