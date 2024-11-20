package com.example.booker.restController;

import com.example.booker.dao.DonHangChiTietDao;
import com.example.booker.entity.DonHangChiTiet;
import com.example.booker.entity.view.DonHangChiTietView;
import com.example.booker.service.nguoidung.DonHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/orderdetail")
public class DonHangChiTietRestController {

    @Autowired
    DonHangChiTietService donHangChiTietService;
    @Autowired
    private DonHangChiTietDao donHangChiTietDao;

    //lấy danh sách hóa đơn chi tiết của cửa hàng theo trạng thái
    @GetMapping("/cuahang-{id}/sort/trangthai-{matt}")
    public List<DonHangChiTiet> sortByMaTrangThai(@PathVariable int id, @PathVariable int matt) {
        return donHangChiTietService.LocDHCTByCuaHangAndByTrangThai(id, matt);
    }

    //lọc hóa đơn chi tiết theo theo ngày tạo
    @GetMapping("/cuahang-{id}/sort/trangthai-{matt}/ngay-{date}")
    public List<DonHangChiTiet> sortByMaNgaytao(@PathVariable int id, @PathVariable int matt, @PathVariable LocalDate date) {
        return donHangChiTietService.LocDHCTByCuahangAndByNgaytao(id, matt, date);
    }

    //lọc hóa đơn chi tiết theo mã hóa đơn
    @GetMapping("/cuahang-{id}/trangthai-{matt}/tim_kiem_by_idhd-{madh}")
    public List<DonHangChiTiet> sortByMadh(@PathVariable int id, @PathVariable int matt, @PathVariable int madh) {
        return donHangChiTietService.LocDHCTByCuahangAndByMaHoaDon(id, matt, madh);
    }

    //lấy thông tin chi tiết của một hóa đơn chi tiết
    @GetMapping("/cuahang-{id}/don_hang_chi_tiet-{madh}")
    public DonHangChiTiet InfoDetalDonHangChiTiet(@PathVariable int id, @PathVariable int madh) {
        return donHangChiTietService.infoDetailDonHangChiTiet(id, madh);
    }

    // cập nhat don hang chi tiet
    @PutMapping("/cap-nhat_don_hang_chi_tiet")
    public DonHangChiTiet update(@RequestBody DonHangChiTiet donHangChiTiet) {
        return donHangChiTietService.updateTrangThai(donHangChiTiet);
    }

    //Lấy doanh thu cửa hàng
    @GetMapping("/cuahang-{id}/trangthai-{matt}")
    public Double getDoanhThu(@PathVariable int id, @PathVariable int matt) {
        return donHangChiTietService.getDoanhThu(id, matt);
    }

    //Đơn hàng MỚI theo 7 ngày gần nhất thuộc cửa hàng
    @GetMapping("/cuahang-{id}/donhang/trangthai-{matt}")
    public List<DonHangChiTiet> getDonHang(@PathVariable int id, @PathVariable int matt) {
        return donHangChiTietService.findDonHangChiTiet(id, matt);
    }

//    Đếm
    @GetMapping("dem_hoa_don_chi_tiet/cuahang-{storeID}/trangthai-{maTrangThai}")
    public Long count(@PathVariable int storeID, @PathVariable int maTrangThai) {
        return donHangChiTietDao.countProductsByStoreAndStatus(storeID, maTrangThai);
    }

//    Lâý tất cả hóa đơn chi tiết thuộc cửa hàng
    @GetMapping("/cuahang-{id}")
    public List<DonHangChiTiet> get(@PathVariable int id) {
        return donHangChiTietDao.findDonHangChiTietByCuaHang(id);
    }

//    ADMIN - lấy đơn hàng chi tiết theo trạng thái
    @GetMapping("/admin/trangthai-{matt}")
    public List<DonHangChiTiet> getAll(@PathVariable int matt) {
        return donHangChiTietDao.getAllDonHangChiTietByTrangThai(matt);
    }


//    @GetMapping("/thongke-donhang/{mach}")
//    public List<DonHangChiTietView> getThongKe(@PathVariable("mach") int mach){
//        return donHangChiTietDao.findOrderCountByStoreAndDate(mach);
//    }

}