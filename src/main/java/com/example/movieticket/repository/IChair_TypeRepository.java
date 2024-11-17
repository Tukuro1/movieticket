package com.example.movieticket.repository;

import com.example.movieticket.model.Chair_Type;
import com.example.movieticket.model.TypeChair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChair_TypeRepository extends JpaRepository<Chair_Type,Long> {
}
