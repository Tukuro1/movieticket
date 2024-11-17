package com.example.movieticket.repository;

import com.example.movieticket.model.Chair_Type;
import com.example.movieticket.model.RoomSchedu_Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomSchedu_DayRepository extends JpaRepository<RoomSchedu_Day,Long> {
}
