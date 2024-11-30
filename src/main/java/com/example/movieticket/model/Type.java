package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name="name",length = 50)
    private String name;
    @OneToMany(mappedBy = "type",fetch = FetchType.LAZY)
    private List<Type_Movie> type_movie;
}
