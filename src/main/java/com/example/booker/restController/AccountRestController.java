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
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AccountRestController {

    @Autowired
    TaiKhoanDao tkDao;

    @GetMapping("/list")
    public List<TaiKhoan> list() {
        return tkDao.findAll();
    }

    @PostMapping()
    public TaiKhoan create(@RequestBody TaiKhoan tk) {
        return tkDao.save(tk);
    }


}
