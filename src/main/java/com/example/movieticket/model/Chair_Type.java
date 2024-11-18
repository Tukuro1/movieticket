package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "chairtype")
public class Chair_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_chair")
    private Chair chair;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_typechair")
    private  TypeChair typeChair;
    @OneToMany(mappedBy = "chair_type" , fetch = FetchType.LAZY)
    private List<Status_Chair> status_chair;
}
