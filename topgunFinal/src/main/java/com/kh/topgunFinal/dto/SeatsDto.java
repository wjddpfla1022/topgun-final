package com.kh.topgunFinal.dto;

import lombok.Data;

@Data
public class SeatsDto {
	private int seatsNo;//번호
	private int flightId; // flight_id 항공편 번호
	private String seatsRank;//좌석등급
	private int seatsPrice;//좌석가격
	private String seatsStatus;//좌석상태
	private String seatsNumber;//좌석번호
}
