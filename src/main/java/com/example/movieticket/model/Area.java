package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name="name",length = 50)
    private String name;
    @OneToMany(mappedBy = "area")
    private List<Branch> branch;
}