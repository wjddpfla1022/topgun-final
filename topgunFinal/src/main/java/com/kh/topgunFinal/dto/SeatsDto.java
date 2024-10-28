package com.kh.topgunFinal.dto;

import lombok.Data;

@Data
public class SeatsDto {
	private int seatsNo;//좌석번호
	private String seatsRank;//좌석등급
	private int seatsPrice;//좌석가격
	private String seatsStatus;//좌석상태
}
