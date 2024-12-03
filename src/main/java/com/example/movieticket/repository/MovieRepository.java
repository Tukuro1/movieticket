package com.example.movieticket.repository;

import com.example.movieticket.model.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    // Tìm phim theo tiêu đề (title)
    List<Movie> findByTitleContaining(String title);

    // Tìm phim nổi bật (highlight)
    List<Movie> findByHighlightTrue();

    @Query("SELECT m FROM Movie m JOIN m.listtype_movies tm WHERE tm.type.id = :typeId")
    List<Movie> findByTypeId(@Param("typeId") Long typeId);

    @Query("SELECT m FROM Movie m JOIN m.listtype_movies tm WHERE tm.type.id IN :typeIds")
    List<Movie> findByTypeIds(@Param("typeIds") List<Long> typeId);
}
