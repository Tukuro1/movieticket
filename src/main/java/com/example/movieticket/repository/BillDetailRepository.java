package com.example.movieticket.repository;

import com.example.movieticket.model.Bill_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<Bill_Detail,Long> {
}
