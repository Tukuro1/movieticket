package com.example.movieticket.repository;

import com.example.movieticket.model.TimeFrame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITimeFrameRepository extends JpaRepository<TimeFrame, Long> {
}
