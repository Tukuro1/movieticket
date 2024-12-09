package com.example.movieticket.repository;
import com.example.movieticket.model.Type_Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMovieRepository extends JpaRepository<Type_Movie, Long> {
    List<Type_Movie> findByIdIn(List<Long> ids);
}
