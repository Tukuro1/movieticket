package com.example.movieticket.dto;

import com.example.movieticket.model.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalTime;

public class ScheduleResponse {

    @JsonIgnore
    public Room room;
    public LocalTime startTime;
    public LocalTime endTime;

    private String roomName;

    public Long id;



    // Constructor
    public ScheduleResponse(Room room, LocalTime startTime, LocalTime endTime, Long id) {
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomName = room.getRoom_number();
        this.id = id;
    }

    // Getters and setters
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
