package com.example.movieticket.repository;

import com.example.movieticket.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAreaRepository extends JpaRepository<Area,Long> {
}
