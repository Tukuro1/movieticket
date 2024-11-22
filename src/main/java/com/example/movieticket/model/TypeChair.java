package com.example.movieticket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "typechair")
public class TypeChair {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @NotBlank(message = "Name is required")
    @Column(name = "type_chair", length = 100, nullable = false)
    @Size(max = 50, message = "Name must be less than 100 characters")
    private String type_chair;
    @OneToMany(mappedBy = "typechair")
    private List<Chair_Type> chair_type;
}
