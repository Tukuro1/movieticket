package com.example.movieticket.dto;

import java.util.List;

import lombok.Data;

@Data
public class PaymentDTO {
    private List<Long> seatIds;
    private Long scheduleId;
}
