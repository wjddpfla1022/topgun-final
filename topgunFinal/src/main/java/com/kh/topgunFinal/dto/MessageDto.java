package com.kh.topgunFinal.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MessageDto {
	private int messageNo;
	private String messageType;
	private String messageSender;
	private String messageReceiver;
	private String messageContent;
	private Timestamp messageTime;
	private int roomNo;
}