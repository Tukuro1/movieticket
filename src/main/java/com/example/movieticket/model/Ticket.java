package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_statusChair")
    private Status_Chair status_chair;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bill")
    private Bill bill;
}
