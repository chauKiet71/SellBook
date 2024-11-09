package com.example.booker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vi_pham")
public class ViPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_vi_pham;
    String ten_vi_pham;
    int diem_vi_pham;
}
