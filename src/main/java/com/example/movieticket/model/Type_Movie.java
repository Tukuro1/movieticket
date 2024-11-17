package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "type_movie")
public class Type_Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
//    @Column(name="id_movie")
//    private int id_movie;
//
//    @Column(name=" id_type")
//    private int  id_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_movie")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_type")
    private Type type;
}
