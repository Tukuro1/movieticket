package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "timeFrame")
public class TimeFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column
    private String starttime;
    @Column
    private String endtime;

    @OneToMany(mappedBy = "timeFrame")
    private List<RoomSchedu_Time> roomschedu_time;
}
