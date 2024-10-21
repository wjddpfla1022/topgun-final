package com.kh.topgunFinal.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.kh.topgunFinal.dao.WebsocketMessageDao;
import com.kh.topgunFinal.dto.WebsocketMessageDto;
import com.kh.topgunFinal.service.TokenService;
import com.kh.topgunFinal.vo.UserClaimVO;
import com.kh.topgunFinal.vo.WebsocketDMResponseVO;
import com.kh.topgunFinal.vo.WebsocketRequestVO;
import com.kh.topgunFinal.vo.WebsocketResponseVO;

@Controller
public class WebsocketController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private TokenService tokenService;
	@Autowired 
	private WebsocketMessageDao websocketMessageDao;
	
	@MessageMapping("/chat")
	public void chat(Message<WebsocketRequestVO> message) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message); 
		String accessToken = accessor.getFirstNativeHeader("accessToken");
		
		if(accessToken == null) {//비회원이 채팅을 보냈으면
			return; //그만
		}
		
		UserClaimVO claimVO = tokenService.check(tokenService.removeBearer(accessToken));
		
		WebsocketRequestVO request = message.getPayload();
		
		WebsocketResponseVO response = new WebsocketResponseVO();
		response.setContent(request.getContent());
		response.setTime(LocalDateTime.now());
		response.setSenderUsersId(claimVO.getUserId());
		response.setSenderUsersType(claimVO.getUserType());
		
		messagingTemplate.convertAndSend("/public/chat", response);
		
		int websocketMessageNo = websocketMessageDao.sequence();
		WebsocketMessageDto websocketMessageDto = new WebsocketMessageDto();
		websocketMessageDto.setWebsocketMessageType("chat");
		websocketMessageDto.setWebsocketMessageNo(websocketMessageNo);
		websocketMessageDto.setWebsocketMessageSender(claimVO.getUserId());
		websocketMessageDto.setWebsocketMessageReceiver(null);
		websocketMessageDto.setWebsocketMessageContent(request.getContent());
		websocketMessageDto.setWebsocketMessageTime(Timestamp.valueOf(response.getTime()));
		websocketMessageDao.insert(websocketMessageDto);
	}
	
	@MessageMapping("/dm/{receiverId}")
	public void dm(@DestinationVariable String receiverId, Message<WebsocketRequestVO> message) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message); 
		String accessToken = accessor.getFirstNativeHeader("accessToken");
		
		if(accessToken == null) {
			return;
		}
		UserClaimVO claimVO = tokenService.check(tokenService.removeBearer(accessToken));
		if(claimVO.getUserId().equals(receiverId)) {
			return;
		}
		WebsocketRequestVO request = message.getPayload();
		
		WebsocketDMResponseVO response = new WebsocketDMResponseVO();
		response.setContent(request.getContent());
		response.setTime(LocalDateTime.now());
		response.setSenderUsersId(claimVO.getUserId());
		response.setSenderUsersType(claimVO.getUserType());
		response.setReceiverUsersId(receiverId); 
		
		messagingTemplate.convertAndSend("/public/dm/"+response.getSenderUsersId(), response);
		messagingTemplate.convertAndSend("/public/dm/"+response.getReceiverUsersId(), response);
		
		int websocketMessageNo = websocketMessageDao.sequence();
		WebsocketMessageDto websocketMessageDto = new WebsocketMessageDto();
		websocketMessageDto.setWebsocketMessageType("dm");
		websocketMessageDto.setWebsocketMessageNo(websocketMessageNo);
		websocketMessageDto.setWebsocketMessageSender(response.getSenderUsersId());
		websocketMessageDto.setWebsocketMessageReceiver(response.getReceiverUsersId()); 
		websocketMessageDto.setWebsocketMessageContent(request.getContent());
		websocketMessageDto.setWebsocketMessageTime(Timestamp.valueOf(response.getTime())); //시간 동기화
		websocketMessageDao.insert(websocketMessageDto);
	}
}
