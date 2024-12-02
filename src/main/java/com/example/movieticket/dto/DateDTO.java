package com.example.movieticket.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateDTO {
    private String dateFormat;
    private LocalDate date;

    public DateDTO(String format, LocalDate date) {
        this.dateFormat = format;
        this.date = date;
    }
}
