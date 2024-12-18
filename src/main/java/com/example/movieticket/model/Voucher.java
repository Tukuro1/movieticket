package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vouchercode;
    @Column(name = "discount")
    private float discount;
    @Column(name = "startday")
    private LocalDate startday;
    @Column(name = " endday")
    private LocalDate endday;
    @Column(name = "quantity")
    private float quantity;
    @Column(name = "detail")
    private String detail;
    @OneToMany(mappedBy = "voucher", fetch = FetchType.LAZY)
    private List<Bill> bill;
    @OneToMany(mappedBy = "voucher", fetch = FetchType.LAZY)
    private List<Voucher_Customer> listvoucher_customer;
}
