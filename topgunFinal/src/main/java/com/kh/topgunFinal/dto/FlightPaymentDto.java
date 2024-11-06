package com.kh.topgunFinal.dto;

import lombok.Data;

@Data
public class FlightPaymentDto {
    private String flightNumber;
    private Integer totalPayment;
    private String airlineName;
}