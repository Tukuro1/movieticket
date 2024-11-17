package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "movie_schedu")
public class Movie_Schedu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
//    @Column(name = "id_movie")
//    private int id_movie;
//    @Column(name = "id_roomSchedu_time")
//    private int id_roomSchedu_time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_roomSchedu_time")
    private RoomSchedu_Time roomschedu_time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_movie")
    private Movie movie;
}
