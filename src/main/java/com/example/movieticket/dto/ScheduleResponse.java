package com.example.movieticket.dto;

import com.example.movieticket.model.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleResponse {

    @JsonIgnore
    public Room room;
    public LocalTime startTime;
    public LocalTime endTime;

    private String roomName;

    private LocalDate date;

    public Long id;



    // Constructor
    public ScheduleResponse(Room room, LocalTime startTime, LocalTime endTime, Long id, LocalDate date) {
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomName = room.getRoom_number();
        this.id = id;
        this.date = date;
    }

}
