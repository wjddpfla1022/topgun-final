package com.kh.topgunFinal.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RoomDto {
	private int roomNo;
	private String RoomName;
	private Timestamp roomCreate;
}