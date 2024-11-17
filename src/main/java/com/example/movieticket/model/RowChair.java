package com.example.movieticket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "rowchair")
public class RowChair {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @NotBlank(message = "Name is required")
    @Column(name = "row_name", length = 100, nullable = false)
    @Size(max = 50, message = "Name must be less than 100 characters")
    private String row_name;
    @NotBlank(message = "Name is required")
    @Column(name = "chair_count", length = 100, nullable = false)
    @Size(max = 50, message = "Name must be less than 100 characters")
    private String chair_count;
    //    @Column
//    private int id_room;
    @ManyToOne
    @JoinColumn(name = "id_room")
    private Room room;
    @OneToMany(mappedBy = "rowchair" , fetch = FetchType.LAZY)
    private List<Chair> chair;
}
