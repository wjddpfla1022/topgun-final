package com.kh.topgunFinal.dto;

import lombok.Data;

@Data
public class FlightPaymentDto {
    private String flightId;
    private Integer totalPayment;
    private String airlineName;
}