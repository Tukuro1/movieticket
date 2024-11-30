package com.example.movieticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "roomschedu_time")
public class RoomSchedu_Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime  startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "ticket_price")
    private float ticketPrice; // Giá vé cho suất chiếu của bộ phim này

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_room")
    private Room room;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_movie")
    private Movie movie;
}
