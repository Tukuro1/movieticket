package com.example.movieticket.repository;

import com.example.movieticket.model.RoomSchedu_Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomScheduTimeRepository extends JpaRepository<RoomSchedu_Time, Long> {
}
