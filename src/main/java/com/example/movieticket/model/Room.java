package com.example.movieticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_branch")
    private Branch branch;
}
