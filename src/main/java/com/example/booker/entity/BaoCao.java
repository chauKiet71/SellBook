package com.example.booker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "id_tai_khoan_bao_cao", referencedColumnName = "id_tai_khoan")
    private TaiKhoan id_tai_khoan_bao_cao;

    @ManyToOne
    @JoinColumn(name = "ma_cua_hang_bao_cao", referencedColumnName = "ma_cua_hang")
    private CuaHang ma_cua_hang_bao_cao;


    @ManyToOne
    @JoinColumn(name = "id_tai_khoan_bi_bao_cao", referencedColumnName = "id_tai_khoan")
    private TaiKhoan id_tai_khoan_bi_bao_cao;

    @ManyToOne
    @JoinColumn(name = "ma_cua_hang_bi_bao_cao", referencedColumnName = "ma_cua_hang")
    private CuaHang ma_cua_hang_bi_bao_cao;


    String noi_dung_vi_phạm;
    Boolean truong_hop; // true là người dùng báo cáo cửa hàng - false: cửa hàng báo cáo ng dùng đánh gia
    Boolean trang_thai_bao_cao;
    Boolean an_thong_bao;
    Boolean xem_thong_bao;

    @ManyToOne
    @JoinColumn(name = "ma_danh_gia")
    ViPham ma_danh_gia;

//    @ManyToOne
//    @JoinColumn(name = "id_vi_pham", insertable=false, updatable=false)
//    ViPham vi_pham;

}
