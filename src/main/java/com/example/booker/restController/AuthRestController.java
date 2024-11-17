package com.example.booker.restController;

import com.example.booker.dao.TaiKhoanDao;
import com.example.booker.entity.TaiKhoan;
import com.example.booker.request.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    TaiKhoanDao taikhoanDao;

    int realOTP;

    @GetMapping("/forgetPassword/nguoidung-{id}/email-{email}")
    public ResponseEntity<Integer> sendCode(@PathVariable("email") String email, @PathVariable("id") int id) {
        TaiKhoan tkNguoiDung = taikhoanDao.findById(id).get();
        if (tkNguoiDung.getEmail().equals(email)) {
            int OTP = (int) Math.floor(((Math.random() * 899999) + 100000));
            String username = "truongson9a2nd@gmail.com";
            String password = "aqlksnunlsjapwvv";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(email)
                );
                message.setSubject("OTP");
                message.setContent("<html><b>"+OTP+"</b></html>" + " là mã xác nhận mật khẩu của bạn", "text/html; charset=utf-8");

                Transport.send(message);
                realOTP = OTP;
                return ResponseEntity.ok(OTP);
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/forgetPassword/nguoidung-{id}/otp-{code}/newPass-{newPass}")
    public ResponseEntity<TaiKhoan> enterNewPassword(@PathVariable("id") int id, @PathVariable("code") int code, @PathVariable("newPass") String newPass) {
        if (taikhoanDao.existsById(id)) {
            if (code == realOTP) {
                TaiKhoan tkNguoiDung = taikhoanDao.findById(id).get();
                tkNguoiDung.setMat_khau(newPass);
                taikhoanDao.save(tkNguoiDung);
                return ResponseEntity.ok(tkNguoiDung);
            }
            else {
                return ResponseEntity.noContent().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
