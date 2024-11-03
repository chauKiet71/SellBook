package com.example.booker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dia_chi")
@AllArgsConstructor
@NoArgsConstructor
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
}
