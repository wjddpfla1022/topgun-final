package com.kh.topgunFinal.vo;

import java.util.List;

import lombok.Data;

@Data
public class MessageMoreVO {
	private boolean last;
	private List<MessageVO> messageList;
}
