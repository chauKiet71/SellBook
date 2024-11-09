package com.example.booker.dao;

import com.example.booker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
}
