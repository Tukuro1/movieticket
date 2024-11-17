package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name="name",length = 50)
    private String name;
    @OneToOne(mappedBy = "type",fetch = FetchType.LAZY)
    private Type_Movie type_movie;
}
