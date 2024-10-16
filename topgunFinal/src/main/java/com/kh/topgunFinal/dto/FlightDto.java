package com.kh.topgunFinal.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FlightDto {
	private int flightId; //항공편 식별키
	private String flightNumber; //항공기 번호
	private String departureTime; //출발시각
	private String arrivalTime; //도착시각
	private LocalDateTime flightTime; //운항시간
	private String departureAirport; //출발공항
	private String arrivalAirport;//도착공항
	private String userId;//항공사 아이디
	private String flightTotalSeat;//총좌석 수
	private String flightStatus;//승인상태
}
