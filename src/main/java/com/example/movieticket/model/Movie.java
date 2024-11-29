package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", length = 50)
    private String title;
    @Column(name = "detail", length = 50)
    private String detail;
    @Column(name = "image")
    private String image;
    @Column(name = "datestart")
    private Date datestart;
    @Column(name = "timeMovie")
    private int timeMovie;
    @Column(name = "highlight")
    private boolean highlight;
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<RoomSchedu_Time> listroomSchedu_times;
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Type_Movie> listtype_movies;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Human_Movie> human_movie = new HashSet<>();

}
