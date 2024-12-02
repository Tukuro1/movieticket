package com.example.movieticket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movieticket.model.Voucher_Customer;
import com.example.movieticket.repository.VoucherCustomerRepository;

@Service
public class VoucherCustomerService {
    @Autowired
    private VoucherCustomerRepository voucherCustomerRepository;

    public List<Voucher_Customer> getAllByUserId(Long userId) {
        return voucherCustomerRepository.findAllByUserId(userId);
    }
}
