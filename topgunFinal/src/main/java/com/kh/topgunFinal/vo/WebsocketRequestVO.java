package com.kh.topgunFinal.vo;

import lombok.Data;

@Data
public class WebsocketRequestVO {
	private String content; //사용자가 보낸 메세지 내용을 수신하기 위한 VO
//	private String receiverUsersId; // 수신자 ID 추가
}
