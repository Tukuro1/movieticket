package com.example.movieticket.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "status_chair")
public class Status_Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name="status",length = 50)
    private String status;
//    @Column(name = "id_chairType")
//    private int id_chairType;
//    @Column(name = "id_roomSchedu_time")
//    private int id_roomSchedu_time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_roomSchedu_time")
    private  RoomSchedu_Time roomschedu_time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chairType", referencedColumnName = "id" )
    private    Chair_Type chair_type;
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_roomSchedu_time", referencedColumnName = "id" )
//    Chair_Type chair_type;


}
