package com.example.movieticket.repository;

import com.example.movieticket.model.RowChair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRowChairRepository extends JpaRepository<RowChair,Long> {
}