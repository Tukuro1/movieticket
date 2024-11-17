package com.example.movieticket.repository;

import com.example.movieticket.model.Voucher_Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherCustomerRepository extends JpaRepository<Voucher_Customer,Long> {
}
