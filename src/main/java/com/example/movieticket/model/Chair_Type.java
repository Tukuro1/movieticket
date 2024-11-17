package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chair_type")
public class Chair_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_chair", referencedColumnName = "id" )
//           Chair chair;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_chair")
    private Chair chair;
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_typechair", referencedColumnName = "id" )
//            TypeChair typeChair;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_typechair")
    private  TypeChair typeChair;
    @OneToOne(mappedBy = "chair_type",fetch = FetchType.LAZY)
    private Status_Chair status_chair;
}
