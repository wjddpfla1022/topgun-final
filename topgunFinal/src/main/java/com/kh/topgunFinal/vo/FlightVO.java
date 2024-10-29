package com.kh.topgunFinal.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kh.topgunFinal.dto.AirlineDto;

import lombok.Data;

@Data
public class FlightVO {
	private AirlineDto airlineDto;
	
    private int flightId; 
    private String flightNumber;
    private String flightTime; 
    private String departureAirport; 
    private String arrivalAirport; 
    private String userId;
    private int flightPrice; 
    private String flightStatus;  
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Seoul")
    private String departureTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Seoul")
    private String arrivalTime; 
    
    private Integer passengers;
}
