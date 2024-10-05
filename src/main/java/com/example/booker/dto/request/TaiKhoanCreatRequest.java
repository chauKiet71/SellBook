package com.example.booker.dto.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaiKhoanCreatRequest {

    Integer id_tai_khoan;

    String email;

    String mat_khau;

    String ho_ten;

    @Temporal(TemporalType.DATE)
    Date ngay_sinh;

    String so_dt;

    @Temporal(TemporalType.DATE)
    Date ngay_tao;
}
