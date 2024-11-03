package com.example.booker.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("*")
public class RestApiThanhToan {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "https://api.web2m.com/historyapiacbv3/Kiet999/12897891/654249E7-3D9B-5306-E6DA-6DA177FD9882";
    private static final String API_KEY = "654249E7-3D9B-5306-E6DA-6DA177FD9882"; // Đặt API key nếu cần

    @GetMapping("/api/v1/get-thanhtoan")
    public ResponseEntity<String> proxyApi() {
        // Thiết lập các headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY); // Thay đổi nếu API yêu cầu header khác

        // Tạo yêu cầu đến API bên thứ ba mà không cần tham số
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Gửi yêu cầu và nhận phản hồi từ API bên thứ ba
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

        return response; // Trả dữ liệu về frontend
    }
}
