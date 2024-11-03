package com.example.booker.service.nguoidung.impl;

import com.example.booker.dao.DonHangChiTietDao;
import com.example.booker.entity.DonHangChiTiet;
import com.example.booker.service.nguoidung.DonHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonHangChiTietImpl implements DonHangChiTietService {

    @Autowired
    DonHangChiTietDao donHangChiTietDao;

    @Override
    public List<DonHangChiTiet> LocDHCTByCuaHangAndByTrangThai(int ma_cua_hang, int ma_trang_thai) {
        return donHangChiTietDao.findByMaTrangThaiAndMaCuaHang(ma_cua_hang, ma_trang_thai);
    }

    @Override
    public List<DonHangChiTiet> LocDHCTByCuahangAndByNgaytao(int ma_cua_hang, int ma_trang_thai, LocalDate ngay_tao) {
        return donHangChiTietDao.sortDonHangChiTietByNgayTao(ma_cua_hang, ma_trang_thai, ngay_tao);
    }

    @Override
    public List<DonHangChiTiet> LocDHCTByCuahangAndByMaHoaDon(int ma_cua_hang, int ma_trang_thai, int ma_don_hang) {
        return donHangChiTietDao.sortDonHangChiTietByMaDonHang(ma_cua_hang, ma_trang_thai, ma_don_hang);
    }

    @Override
    public DonHangChiTiet infoDetailDonHangChiTiet(int ma_cua_hang, int ma_don_hang_ct) {
        return donHangChiTietDao.InfoDetailDonHangChiTiet(ma_cua_hang, ma_don_hang_ct);
    }

    @Override
    public DonHangChiTiet updateTrangThai(DonHangChiTiet trangThai) {
        return donHangChiTietDao.save(trangThai);
    }

    @Override
    @Transactional
    public Double getDoanhThu(int ma_cua_hang, int ma_trang_thai) {
        return donHangChiTietDao.GetDoanhThu(ma_cua_hang, ma_trang_thai);
    }

    @Override
    public List<DonHangChiTiet> findDonHangChiTiet(int ma_cua_hang, int ma_trang_thai) {
        return donHangChiTietDao.findDonHangChiTiet(ma_cua_hang, ma_trang_thai);
    }
}
