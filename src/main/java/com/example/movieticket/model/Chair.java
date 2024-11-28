package com.example.movieticket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Entity
@Table(name = "chair")
public class Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "chair_name", length = 100, nullable = false)
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String chair_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rowchair")
    private RowChair row_chair;

    @Column(name = "priority", nullable = true)
    private int priority;

    @OneToOne(mappedBy = "chair", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Chair_Type chair_type;

    @OneToOne(mappedBy = "chair", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Status_Chair status_chair;
}
