package com.example.booker.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "save_voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_voucher;
    private int id_tai_khoan;

    @ManyToOne
    @JoinColumn(name = "id_voucher", insertable = false, updatable = false)
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "id_tai_khoan", insertable = false, updatable = false)
    private TaiKhoan tai_khoan;


}
