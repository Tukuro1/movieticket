package com.example.movieticket.model;

import java.util.Objects;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "status_chair")
public class Status_Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_roomSchedu_time")
    private RoomSchedu_Time roomschedu_time;

    // Quan hệ One-to-One với Chair
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_chair", referencedColumnName = "id")
    private Chair chair;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Status_Chair that = (Status_Chair) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Chỉ cần so sánh dựa trên id
    }
}
