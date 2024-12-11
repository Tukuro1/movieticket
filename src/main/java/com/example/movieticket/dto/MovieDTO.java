package com.example.movieticket.dto;

import com.example.movieticket.model.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovieDTO {
    private Long id;
    private String title;
    private String detail;
    private String image;
    private LocalDate datestart;
    private int timeMovie;
    private boolean highlight;
    private String trailerUrl;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.detail = movie.getDetail();
        this.datestart = movie.getDatestart();
        this.timeMovie = movie.getTimeMovie();
        this.highlight = movie.isHighlight();
        
    }
}
