package com.example.demo.repository;

import com.example.demo.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransferRepository extends JpaRepository<Transfer, String> {
}
