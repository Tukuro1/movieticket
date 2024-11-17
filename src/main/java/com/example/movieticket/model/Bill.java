package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name="datebill")
    private Date datebill;
    @Column(name="id_customer")
    private int id_customer;
    @Column(name=" totalPrice")
    private float totalPrice;
    @Column(name=" status")
    private int status;
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private Set<Bill_Detail> bills = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vouchercode")
    private Voucher voucher;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
