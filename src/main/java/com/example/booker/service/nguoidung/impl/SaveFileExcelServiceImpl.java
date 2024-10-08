package com.example.booker.service.nguoidung.impl;

import com.example.booker.dao.SanPhamDao;
import com.example.booker.entity.SanPham;
import com.example.booker.service.nguoidung.SaveFileExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.stream.FileImageOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class SaveFileExcelServiceImpl implements SaveFileExcelService {

    @Autowired
    SanPhamDao sanPhamDao;

    @Override
    public void saveSanPhamExcel(int ma_cua_hang, String filePath) {
        List<SanPham> listSp = sanPhamDao.findAllSanPhamByCuahang(ma_cua_hang);

        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            //Tao header
            Row headerRow = sheet.createRow(0);
            String[] header = {"Mã sản phẩm", "Tên sản phẩm", "Mô tả", "Số lượng hàng", "Giá", "Ngày tạo",
                            "Tên thể loại", "Tác giả", "Ngày xuất bản", "Số trang", "Mã ISBN",
                            "Phiên bản", "Ảnh sản phẩm", "Mã cửa hàng", "Tên cửa hàng"};
            for (int col = 0 ; col < header.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(header[col]);
                CellStyle headerStyle = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                headerStyle.setFont(font);
                cell.setCellStyle(headerStyle);
            }
            //Tao data row
            int rowIndex = 1;
            for (SanPham sanPham: listSp) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(sanPham.getMa_san_pham());
                row.createCell(1).setCellValue(sanPham.getTen_san_pham());
                row.createCell(2).setCellValue(sanPham.getMo_ta());
                row.createCell(3).setCellValue(sanPham.getSo_luong_hang());
                row.createCell(4).setCellValue(sanPham.getGia());
                row.createCell(5).setCellValue(sanPham.getNgay_tao());
                row.createCell(6).setCellValue(sanPham.getThe_loai().getTen_the_loai());
                row.createCell(7).setCellValue(sanPham.getTac_gia());
                row.createCell(8).setCellValue(sanPham.getNgay_xuat_ban());
                row.createCell(9).setCellValue(sanPham.getSo_trang());
                row.createCell(10).setCellValue(sanPham.getMa_isbn());
                row.createCell(11).setCellValue(sanPham.getPhien_ban());
                row.createCell(12).setCellValue(sanPham.getAnh_san_pham());
                row.createCell(13).setCellValue(sanPham.getMa_cua_hang());
                row.createCell(14).setCellValue(sanPham.getCua_hang().getTen_cua_hang());
            }
            for (int i = 0; i<header.length; i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(fileOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
