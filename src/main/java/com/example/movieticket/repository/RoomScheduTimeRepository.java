package com.example.movieticket.repository;

import com.example.movieticket.model.RoomSchedu_Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomScheduTimeRepository extends JpaRepository<RoomSchedu_Time, Long> {
    @Query("SELECT r FROM RoomSchedu_Time r WHERE r.date >= :date ORDER BY r.date ASC")
    List<RoomSchedu_Time> findByDateGreaterThanEqual(@Param("date") LocalDate date);

    @Query("SELECT r FROM RoomSchedu_Time r WHERE r.date = :date ORDER BY r.date ASC")
    List<RoomSchedu_Time> findByDateEqual(@Param("date") LocalDate date);
}
