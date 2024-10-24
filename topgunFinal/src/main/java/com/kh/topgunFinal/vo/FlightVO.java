package com.kh.topgunFinal.vo;

import java.sql.Timestamp;
import com.kh.topgunFinal.dto.AirlineDto;
import lombok.Data;

@Data
public class FlightVO {
	private AirlineDto airlineDto;
    private int flightId; 
    private String flightNumber; 
    private Timestamp departureTime; 
    private Timestamp arrivalTime; 
    private String flightTime; 
    private String departureAirport; 
    private String arrivalAirport; 
    private String userId;
    private int flightPrice; 
    private String flightStatus;  
}
