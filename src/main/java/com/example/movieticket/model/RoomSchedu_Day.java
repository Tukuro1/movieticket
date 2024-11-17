package com.example.movieticket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "roomschedu_day")
public class RoomSchedu_Day {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @NotBlank(message = "Name is required")
    @Column(name = "dateshow", length = 100, nullable = false)
    @Size(max = 50, message = "Name must be less than 100 characters")
    private Date dateshow;

//    @Column
//    private int id_room;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_room" , referencedColumnName = "id" )
            Room room;
    @OneToOne(mappedBy = "roomschedu_day",fetch = FetchType.LAZY)
    private RoomSchedu_Time roomschedu_time;
}
