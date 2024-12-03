package com.example.movieticket.repository;

import com.example.movieticket.model.Voucher_Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherCustomerRepository extends JpaRepository<Voucher_Customer, Long> {

    @Query("SELECT vc FROM Voucher_Customer vc WHERE vc.user.id = :userId")
    List<Voucher_Customer> findAllByUserId(Long userId);
}
