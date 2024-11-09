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
@Table(name = "bao_cao")
public class BaoCao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_bao_cao;
    int id_tai_khoan_bao_cao;
    int id_tai_khoan_bi_bao_cao;
    String noi_dung_khac;
    Boolean truong_hop; // true là người dùng báo cáo cửa hàng - false: cửa hàng báo cáo ng dùng đánh gia
    Boolean trang_thai_bao_cao;

    @ManyToOne
    @JoinColumn(name = "id_vi_pham", insertable=false, updatable=false)
    ViPham vi_pham;
}
