package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "roomschedu_time")
public class RoomSchedu_Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    //    @Column(name = "id_roomSchedu_day")
//    private int id_roomSchedu_day;
//    @Column(name = "id_timeFrame")
//    private int id_timeFrame;
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_roomSchedu_day", referencedColumnName = "id" )
//            RoomSchedu_Day roomschedu_day;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_roomSchedu_day")
    private  RoomSchedu_Day roomschedu_day;
    //    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_timeFrame", referencedColumnName = "id" )
//    TimeFrame timeFrame;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_timeFrame")
    private  TimeFrame timeFrame;
    @OneToMany(mappedBy = "roomschedu_time" , fetch = FetchType.LAZY)
    private List<Movie_Schedu> movie_schedu;
    @OneToMany(mappedBy = "roomschedu_time" , fetch = FetchType.LAZY)
    private List<Status_Chair> status_chair;
}
