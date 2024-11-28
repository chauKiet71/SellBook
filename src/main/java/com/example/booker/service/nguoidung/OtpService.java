package com.example.booker.service.nguoidung;

import com.example.booker.entity.Otp;
import com.example.booker.Repo.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    // Tạo và lưu OTP
    public void createOtp(String email, String otp) {
        // Xử lý email trước khi lưu
        String cleanedEmail = email.trim(); // Loại bỏ khoảng trắng dư thừa
        Otp otpRecord = otpRepository.findByEmail(cleanedEmail);
        if (otpRecord == null) {
            otpRecord = new Otp();
            otpRecord.setEmail(cleanedEmail);
        }
        otpRecord.setOtp(otp);
        otpRecord.setExpirationTime(LocalDateTime.now().plusMinutes(5)); // 5 phút hết hạn
        otpRepository.save(otpRecord);
    }


    // Lấy OTP hợp lệ theo email
    public String getValidOtp(String email) {
        String cleanedEmail = email.trim();
        Otp otpRecord = otpRepository.findByEmail(cleanedEmail);
        if (otpRecord != null && otpRecord.getExpirationTime().isAfter(LocalDateTime.now())) {
            return otpRecord.getOtp(); // Trả về mã OTP hợp lệ
        }
        return null; // Không có mã OTP hợp lệ
    }


    // Xóa OTP sau khi đã sử dụng
    public void deleteOtp(String email) {
        Otp otp = otpRepository.findByEmail(email);
        if (otp != null) {
            otpRepository.delete(otp);
        }
    }
}

