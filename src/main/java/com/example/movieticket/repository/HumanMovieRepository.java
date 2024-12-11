package com.example.movieticket.repository;

import com.example.movieticket.model.Human_Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanMovieRepository extends JpaRepository<Human_Movie, Long> {
    List<Human_Movie> findByIdIn(List<Long> ids);
}
