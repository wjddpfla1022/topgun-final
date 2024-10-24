package com.kh.topgunFinal.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.kh.topgunFinal.dao.RoomMessageDao;
import com.kh.topgunFinal.dao.WebsocketMessageDao;
import com.kh.topgunFinal.service.TokenService;
import com.kh.topgunFinal.vo.UserClaimVO;
import com.kh.topgunFinal.vo.WebsocketMessageMoreVO;
import com.kh.topgunFinal.vo.WebsocketMessageVO;

@Service
public class WebsocketEventHandler {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private WebsocketMessageDao websocketMessageDao;
	@Autowired
	private RoomMessageDao roomMessageDao;

	private Map<String, String> userList = Collections.synchronizedMap(new HashMap<>());
	
	@EventListener
	public void whenUserEnter(SessionConnectEvent event) {
		StompHeaderAccessor accessor = 
				StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = accessor.getSessionId();
		String accessToken = accessor.getFirstNativeHeader("accessToken");
		if(accessToken == null) return;
		
		UserClaimVO claimVO = tokenService.check(tokenService.removeBearer(accessToken));
		
		userList.put(sessionId, claimVO.getUserId());
	}
	
	@EventListener
	public void whenUserSubscribe(SessionSubscribeEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		if(accessor.getDestination() == null) return;
		
		if("/public/users".equals(accessor.getDestination())) {
			Set<String> values = new TreeSet<>(userList.values());
			messagingTemplate.convertAndSend("/public/users", values);			
		}
		
//		else if(accessor.getDestination().equals("/public/db")) { //비회원
//			List<WebsocketMessageVO> messageList = websocketMessageDao.selectListMemberComplete(null, 1, 100);
//			if(messageList.isEmpty()) return;
//			
//			List<WebsocketMessageVO> prevMessageList = 
//					websocketMessageDao.selectListMemberComplete(null, 1, 100, messageList.get(0).getNo());
//			
//			WebsocketMessageMoreVO moreVO = new WebsocketMessageMoreVO();
//			moreVO.setMessageList(messageList);
//			moreVO.setLast(prevMessageList.isEmpty());
//			
//			messagingTemplate.convertAndSend("/public/db/"+moreVO);
//		}
//		else if(accessor.getDestination().startsWith("/public/db")) { //회원
//			String usersId = accessor.getDestination().substring("/public/db/".length());
//			
//			List<WebsocketMessageVO> messageList = 
//					websocketMessageDao.selectListMemberComplete(usersId, 1, 100);
//			if(messageList.isEmpty()) return;
//			
//			List<WebsocketMessageVO> prevMessageList = 
//					websocketMessageDao.selectListMemberComplete(usersId, 1, 100, messageList.get(0).getNo());
//			
//			WebsocketMessageMoreVO moreVO = new WebsocketMessageMoreVO();
//			moreVO.setMessageList(messageList);
//			moreVO.setLast(prevMessageList.isEmpty());
//			
//			messagingTemplate.convertAndSend("/public/db/"+usersId,moreVO);			
//		}
		
		else if(accessor.getDestination().startsWith("/private/db")) {
			String removeStr = accessor.getDestination().substring("/private/db/".length());
			int slash = removeStr.indexOf("/");
			int roomNo = Integer.parseInt(removeStr.substring(0, slash)); //슬래시 앞부분
			String usersId = removeStr.substring(slash + 1); //슬래시 뒷부분
			
			//전달할 정보를 조회
			List<WebsocketMessageVO> messageList = 
								roomMessageDao.selectListMemberComplete(usersId, 1, 100, roomNo) ;
			
			WebsocketMessageMoreVO moreVO = new WebsocketMessageMoreVO();
			moreVO.setMessageList(messageList);
			if(messageList.size() > 0) {//메세지가 있다면
				List<WebsocketMessageVO> prevMessageList = 
						roomMessageDao.selectListMemberComplete(usersId, 1, 100, roomNo, messageList.get(0).getNo());
				moreVO.setLast(prevMessageList.isEmpty());
			}
			//전송
			messagingTemplate.convertAndSend("/private/db/"+roomNo+"/"+usersId, moreVO);
		}
		}
	
	@EventListener
	public void whenUserLeave(SessionDisconnectEvent event) {
		StompHeaderAccessor accessor = 
				StompHeaderAccessor.wrap(event.getMessage());//분석해!
		String sessionId = accessor.getSessionId();

		userList.remove(sessionId);
		
		Set<String> values = new TreeSet<>(userList.values());
		messagingTemplate.convertAndSend("/public/users", values);
	}
		
}
