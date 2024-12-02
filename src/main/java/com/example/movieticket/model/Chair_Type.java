package com.example.movieticket.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "chairtype")
public class Chair_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "price_more")
    private float priceMore;

    @OneToMany(mappedBy = "chair_type", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Chair> chairs;
}