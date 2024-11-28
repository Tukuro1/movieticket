package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "chairtype")
public class Chair_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_chair")
    private Chair chair;

    @Column(name = "price_more")
    private float priceMore;
}