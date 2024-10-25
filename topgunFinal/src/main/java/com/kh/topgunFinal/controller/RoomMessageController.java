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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kh.topgunFinal.dao.RoomDao;
import com.kh.topgunFinal.dao.RoomMessageDao;
import com.kh.topgunFinal.dto.RoomMemberDto;
import com.kh.topgunFinal.dto.RoomMessageDto;
import com.kh.topgunFinal.service.TokenService;
import com.kh.topgunFinal.vo.UserClaimVO;
import com.kh.topgunFinal.vo.WebsocketDMResponseVO;
import com.kh.topgunFinal.vo.WebsocketMessageVO;
import com.kh.topgunFinal.vo.WebsocketRequestVO;
import com.kh.topgunFinal.vo.WebsocketResponseVO;

@Controller
public class RoomMessageController {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private RoomMessageDao roomMessageDao;
	@Autowired
	private RoomDao roomDao;
	
	@MessageMapping("/room/{roomNo}")
	public void chat(@DestinationVariable int roomNo, Message<WebsocketRequestVO> message) {
		//토큰 
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		String accessToken = accessor.getFirstNativeHeader("accessToken");
		if(accessToken == null) return;
		
		UserClaimVO claimVO = tokenService.check(tokenService.removeBearer(accessToken));
		
		RoomMemberDto roomMemberDto = new RoomMemberDto();
		roomMemberDto.setUsersId(claimVO.getUserId());
		roomMemberDto.setRoomNo(roomNo);
		boolean canEnter = roomDao.check(roomMemberDto);
		if(canEnter == false) return;
		
		WebsocketRequestVO request = message.getPayload();
		
		//메세지 발송
		WebsocketResponseVO response = new WebsocketResponseVO();
		response.setSenderUsersId(claimVO.getUserId());
		response.setSenderUsersType(claimVO.getUserType());
		response.setTime(LocalDateTime.now());
		response.setContent(request.getContent());
		
		messagingTemplate.convertAndSend("/private/chat/"+roomNo, response);
		
		// DB 저장
	    int messageNo = roomMessageDao.sequence();
	    RoomMessageDto messageDto = new RoomMessageDto();
	    messageDto.setRoomMessageNo(messageNo);
	    messageDto.setRoomMessageType("chat");
	    messageDto.setRoomMessageSender(claimVO.getUserId());
	    messageDto.setRoomMessageReceiver(null);
	    messageDto.setRoomMessageContent(request.getContent());
	    messageDto.setRoomMessageTime(Timestamp.valueOf(response.getTime()));
	    messageDto.setRoomNo(roomNo);

	    // 여기서 insert 호출
	    roomMessageDao.insert(messageDto);
	}
	
	@MessageMapping("/room/{roomNo}/dm/{receiverId}")
	public void sendDM(@DestinationVariable int roomNo, @DestinationVariable String receiverId, 
										Message<WebsocketRequestVO> message) {
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
		
		messagingTemplate.convertAndSend("/private/dm/"+response.getSenderUsersId(), response);
		messagingTemplate.convertAndSend("/private/dm/"+response.getReceiverUsersId(), response);
		
		 // DB에 저장
	    int messageNo = roomMessageDao.sequence();
	    RoomMessageDto messageDto = new RoomMessageDto();
	    messageDto.setRoomMessageNo(messageNo);
	    messageDto.setRoomMessageType("dm");
	    messageDto.setRoomMessageSender(claimVO.getUserId());
	    messageDto.setRoomMessageReceiver(receiverId);
	    messageDto.setRoomMessageContent(request.getContent());
	    messageDto.setRoomMessageTime(Timestamp.valueOf(response.getTime()));
	    messageDto.setRoomNo(roomNo);
	    
	    roomMessageDao.insert(messageDto);
	}
}
