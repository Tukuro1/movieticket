package com.example.movieticket.repository;

import com.example.movieticket.model.Chair_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairTypeRepository extends JpaRepository<Chair_Type,Long> {
}
