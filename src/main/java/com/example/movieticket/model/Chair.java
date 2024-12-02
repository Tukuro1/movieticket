package com.example.movieticket.model;

import java.util.Objects;

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
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chair_type_id") // Foreign key column in Chair table
    private Chair_Type chair_type;

    @OneToOne(mappedBy = "chair", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Status_Chair status_chair;

    private Boolean status;

    private Float tempPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Chair chair = (Chair) o;
        return Objects.equals(id, chair.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Chỉ cần so sánh dựa trên id
    }
}
