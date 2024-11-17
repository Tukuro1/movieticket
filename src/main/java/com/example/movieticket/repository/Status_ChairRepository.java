package com.example.movieticket.repository;

import com.example.movieticket.model.Status_Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Status_ChairRepository extends JpaRepository<Status_Chair,Long> {
}
