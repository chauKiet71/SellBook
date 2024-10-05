package com.example.booker.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

//    @JsonIgnore
//    @OneToMany(mappedBy = "cua_hang")
//    List<SanPhams> sanPhams;
}
