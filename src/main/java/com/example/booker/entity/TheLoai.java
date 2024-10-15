package com.example.booker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "the_loai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheLoai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ma_the_loai")
    int ma_the_loai;

    @NotEmpty(message = "CATEGORY_INVALED")
    String ten_the_loai;

    @NotEmpty(message = "INVALID_CATEGORY")
    String mo_ta_the_loai;

    @JsonIgnore
    @OneToMany(mappedBy = "the_loai")
    List<SanPham> sanPhams;
}
