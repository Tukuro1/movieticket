package com.example.movieticket.repository;

import com.example.movieticket.model.Area;
import com.example.movieticket.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepository extends JpaRepository<Room,Long> {
}