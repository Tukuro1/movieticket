package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "voucher_customer")
public class Voucher_Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
//    @Column(name="id_Voucher")
//    private int id_Voucher;
//
//    @Column(name=" id_Customer")
//    private int  id_Customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_Voucher")
    private Voucher voucher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_Customer")
    private User user;
}
