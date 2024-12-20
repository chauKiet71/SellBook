package com.example.booker.Repo;

import com.example.booker.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findByEmail(String email);
}
