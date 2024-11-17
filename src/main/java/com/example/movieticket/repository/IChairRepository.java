package com.example.movieticket.repository;

import com.example.movieticket.model.Chair;
import com.example.movieticket.model.RowChair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChairRepository extends JpaRepository<Chair,Long> {
}
