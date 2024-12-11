package com.example.movieticket.repository;
import com.example.movieticket.model.Type_Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMovieRepository extends JpaRepository<Type_Movie, Long> {
    @Query("SELECT tm FROM Type_Movie tm WHERE tm.movie.id IN :ids")
    List<Type_Movie> findByMovieIdIn(List<Long> ids);
}
