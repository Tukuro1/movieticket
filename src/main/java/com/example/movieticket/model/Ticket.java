package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
//    @Column(name="id_movieSchedu")
//    private int id_movieSchedu;
//
//    @Column(name=" id_statusChair")
//    private int  id_statusChair;
    @Column(name="detail",length = 150)
    private String detail;
    @Column(name="price")
    private float price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_movieSchedu")
    private Movie_Schedu movie_schedu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_statusChair")
    private Status_Chair status_chair;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Set<Bill_Detail>bill_detail = new HashSet<>();
}
