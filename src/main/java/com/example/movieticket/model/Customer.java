//package com.example.movieticket.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "customer")
//public class Customer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY )
//    private Long id;
//    @Column(name="fullname",length = 50)
//    private String fullname;
//    @Column(name="email",length = 50)
//    private String email;
//    @Column(name="phone",length = 50)
//    private String phone;
//    @Column(name="username",length = 50)
//    private String username;
//    @Column(name="password",length = 50)
//    private String password;
//    @Column(name="customerpoint",length = 50)
//    private int customerpoint;
//    @OneToMany(mappedBy = "customer" , fetch = FetchType.LAZY)
//    private List<Voucher_Customer> listvoucher_customers;
//}