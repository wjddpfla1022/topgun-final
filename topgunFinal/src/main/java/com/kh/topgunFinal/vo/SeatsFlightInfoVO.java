package com.kh.topgunFinal.vo;

import lombok.Data;

@Data
public class SeatsFlightInfoVO {
	private String seatsNo;
    private String seatsRank;
    private String seatsNumber;
    private int seatsPrice;
    private String seatsStatus;
    private String flightId;
    private String airlineName;
    private String departureTime;
    private String arrivalTime;
    private String flightTime;
    private String departureAirport;
    private String arrivalAirport;
    private int flightPrice;
}
