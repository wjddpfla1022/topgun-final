package com.kh.topgunFinal.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class WebsocketMessageVO {
	private int no;
	private String type;
	private String senderUsersId; //발신자
	private String senderUsers; //발신자의 등급
	private String receiverUsersId; //수신자
	private String content; //내용
	private LocalDateTime time; //보낸 시각
}