package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "timeFrame")
public class TimeFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column
    private Date starttime;
    @Column
    private Date endtime;
    @OneToOne(mappedBy = "timeFrame",fetch = FetchType.LAZY)
    private RoomSchedu_Time roomschedu_time;
}
