package com.example.movieticket.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


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

    @Column(name = "priority", nullable = true)
    private Integer priority;

    @NotBlank(message = "Name is required")
    @Column(name = "chair_count", length = 100, nullable = false)
    @Size(max = 50, message = "Name must be less than 100 characters")
    private String chair_count;
    @ManyToOne
    @JoinColumn(name = "id_room")
    private Room room;

    @OneToMany(mappedBy = "row_chair", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Chair> chairs;
}
