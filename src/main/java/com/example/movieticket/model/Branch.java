package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name = "address", length = 100)
    private String address;
    @Column(name = "hotlline", length = 10)
    private String hotlline;

    @ManyToOne
    @JoinColumn(name = "id_area")
    private Area area;
}
