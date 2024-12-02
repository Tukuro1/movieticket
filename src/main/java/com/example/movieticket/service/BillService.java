package com.example.movieticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.movieticket.model.Bill;
import com.example.movieticket.repository.BillRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public Bill save(Bill bill) {
        return billRepository.save(bill); // Lưu bill vào database
    }

    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id); // Tìm bill theo ID
    }

    public List<Bill> findAll() {
        return billRepository.findAll(); // Lấy tất cả hóa đơn
    }

    public Page<Bill> findBySearch(String searchTerm, int page, int size, String sort) {
        // Xác định sort direction (ASC hoặc DESC)
        Sort sortOrder = Sort.by(Sort.Order.asc(sort)); // Mặc định sắp xếp theo thứ tự tăng dần
        if (sort.startsWith("-")) {
            sortOrder = Sort.by(Sort.Order.desc(sort.substring(1))); // Nếu sort có dấu "-" thì sắp xếp giảm dần
        } else if (sort.equals("totalPrice")) {
            sortOrder = Sort.by(Sort.Order.desc("totalPrice")); // Sắp xếp giảm dần theo totalPrice
        } else if (sort.equals("date")) {
            sortOrder = Sort.by(Sort.Order.desc("datebill")); // Sắp xếp giảm dần theo datebill
        } else if (sort.equals("user")) {
            sortOrder = Sort.by(Sort.Order.asc("user.username")); // Sắp xếp theo user (theo tên user)
        }

        // Tạo PageRequest với sort order
        PageRequest pageRequest = PageRequest.of(page, size, sortOrder);

        return billRepository.findAll(pageRequest);
    }

    public void complete(Long id) {
        Optional<Bill> billOptional = billRepository.findById(id);
        if (billOptional.isPresent()) {
            Bill bill = billOptional.get();
            bill.setStatus(1);
            billRepository.save(bill);
        }
    }
}
