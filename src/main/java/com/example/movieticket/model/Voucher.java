package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long vouchercode;
    @Column(name="discount")
    private float discount;
    @Column(name="startday")
    private Date startday;
    @Column(name=" endday")
    private Date endday;
    @Column(name="pricepoint")
    private float pricepoint;
    @Column(name="detail")
    private String detail;
    @OneToMany(mappedBy = "voucher" , fetch = FetchType.LAZY)
    private List<Bill> bill;
    @OneToMany(mappedBy = "voucher" , fetch = FetchType.LAZY)
    private List<Voucher_Customer> listvoucher_customer;
}
