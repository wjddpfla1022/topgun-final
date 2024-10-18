package com.kh.topgunFinal;

import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.topgunFinal.service.PayService;
import com.kh.topgunFinal.vo.pay.PayReadyRequestVO;
import com.kh.topgunFinal.vo.pay.PayReadyResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Paytest03 {

	@Autowired
	private PayService payService;
	
	@Test
	public void test() throws URISyntaxException {
		//준비 PayReadyReqeusetVO
		PayReadyRequestVO request = new PayReadyRequestVO();
		request.setPartnerOrderId(UUID.randomUUID().toString());
		request.setPartnerUserId("testuser1");
		request.setItemName("테스트결제");
		request.setTotalAmount(990000);
		
		//처리
		PayReadyResponseVO response= payService.ready(request);
		
		//출력
		log.info("url={}", response.getNextRedirectPcUrl());
		log.info("tid={}", response.getTid());
		log.info("partner_order_id={}", request.getPartnerOrderId());
		log.info("partner_user_id={}", request.getPartnerUserId());
	}
}
