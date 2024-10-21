package com.kh.topgunFinal.vo;

import java.util.List;

import lombok.Data;

@Data
public class WebsocketMessageMoreVO {
	private boolean last;
	private List<WebsocketMessageVO> messageList;
}
