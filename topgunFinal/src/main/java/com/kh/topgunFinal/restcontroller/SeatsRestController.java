package com.kh.topgunFinal.restcontroller;


import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.topgunFinal.dao.SeatsDao;
import com.kh.topgunFinal.dto.SeatsDto;
import com.kh.topgunFinal.error.TargetNotFoundException;
import com.kh.topgunFinal.service.PayService;
import com.kh.topgunFinal.service.TokenService;
import com.kh.topgunFinal.vo.SeatsApproveRequestVO;
import com.kh.topgunFinal.vo.SeatsPurchaseRequestVO;
import com.kh.topgunFinal.vo.SeatsQtyVO;
import com.kh.topgunFinal.vo.UserClaimVO;
import com.kh.topgunFinal.vo.pay.PayApproveRequestVO;
import com.kh.topgunFinal.vo.pay.PayApproveResponseVO;
import com.kh.topgunFinal.vo.pay.PayReadyRequestVO;
import com.kh.topgunFinal.vo.pay.PayReadyResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/seats")
public class SeatsRestController {

	@Autowired
	private SeatsDao seatsDao;
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private TokenService tokenService;
	
	//조회
	@GetMapping("/")
	public List<SeatsDto>list(){
		return seatsDao.selectList();
	}
	
	//구매
	@PostMapping("/purchase")
	public PayReadyResponseVO purchase(
			@RequestHeader("Authorization") String token,//토큰불러옴
			@RequestBody SeatsPurchaseRequestVO request) throws URISyntaxException {
		UserClaimVO claimVO = 
				tokenService.check(tokenService.removeBearer(token));
		
		StringBuffer buffer = new StringBuffer();
		int total=0;
		for(SeatsQtyVO vo : request.getSeatsList()) {
			SeatsDto seatsDto = seatsDao.selectOne(vo.getSeatsNo());
			if(seatsDto==null) throw new TargetNotFoundException("결제 대상 없음");
			total += seatsDto.getSeatsPrice() * vo.getQty();
			if(buffer.isEmpty()) {
				buffer.append(seatsDto.getSeatsRank());
			}
		}
		if(request.getSeatsList().size()>=2) {
			buffer.append(" 외 " +(request.getSeatsList().size()-1)+"건");
		}
		
		PayReadyRequestVO requestVO = new PayReadyRequestVO();
		requestVO.setPartnerOrderId(UUID.randomUUID().toString());
		requestVO.setPartnerUserId(claimVO.getUserId());
		requestVO.setItemName(buffer.toString());
		requestVO.setTotalAmount(total);
		requestVO.setApprovalUrl(request.getApprovalUrl());
		requestVO.setCancelUrl(request.getCancelUrl());
		requestVO.setFailUrl(request.getCancelUrl());
		
		PayReadyResponseVO responseVO = payService.ready(requestVO);
		
		return responseVO;
	}

	@PostMapping("/approve")
	public PayApproveResponseVO approve(
			@RequestHeader ("Authorization") String token,
			@RequestBody SeatsApproveRequestVO request) throws URISyntaxException {
	
		UserClaimVO claimVO = tokenService.check(tokenService.removeBearer(token));
		
		PayApproveRequestVO requestVO = new PayApproveRequestVO();
		requestVO.setPartnerOrderId(request.getPartnerOrderId());
		requestVO.setPartnerUserId(claimVO.getUserId());
		requestVO.setTid(request.getTid());
		requestVO.setPgToken(request.getPgToken());
		
		PayApproveResponseVO responseVO = payService.approve(requestVO);
		return responseVO;
	}
			
}
