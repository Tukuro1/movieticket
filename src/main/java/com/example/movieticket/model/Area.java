package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name="name",length = 50)
    private String name;
}