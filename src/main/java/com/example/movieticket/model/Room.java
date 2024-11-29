package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name = "room_number", length = 10)
    private String room_number;
    @Column(name = "row_count")
    private String row_count;
    @ManyToOne
    @JoinColumn(name = "id_branch")
    private Branch branch;



}