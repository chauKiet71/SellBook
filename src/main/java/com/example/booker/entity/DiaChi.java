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
@Getter @Setter
public class DiaChi {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ma_dia_chi;
    String dia_chi_gian_hang;

    @ManyToOne @JoinColumn(name = "id_tai_khoan", insertable = false, updatable = false)
    TaiKhoan tai_khoan;

    @JsonIgnore
    @OneToMany(mappedBy = "dia_chi")
    List<DiaChi> diaChis;

}
