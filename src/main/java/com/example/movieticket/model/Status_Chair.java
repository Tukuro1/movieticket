package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "status_chair")
public class Status_Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_roomSchedu_time")
    private RoomSchedu_Time roomschedu_time;

    // Quan hệ One-to-One với Chair
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chair", referencedColumnName = "id")
    private Chair chair;
}
