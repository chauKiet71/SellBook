package com.example.booker.mapper;

import com.example.booker.dto.request.TaiKhoanCreatRequest;
import com.example.booker.entity.TaiKhoan;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TaiKhoanMapper {
    TaiKhoan toTaiKhoans(TaiKhoanCreatRequest request);
}
