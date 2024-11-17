package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "director_actor")
public class Director_Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name="name",length = 50)
    private String name;
    @Column(name="detail",length = 150)
    private String detail;
    @Column(name=" age")
    private String age;


}
