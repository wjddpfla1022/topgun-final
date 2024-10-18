package com.kh.topgunFinal;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.topgunFinal.service.PayService;
import com.kh.topgunFinal.vo.pay.PayApproveResponseVO;
import com.kh.topgunFinal.vo.pay.PayApproveRequestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PayTest04 {

	@Autowired
	PayService payService;
	
	@Test
	public void etst() throws URISyntaxException {
		//준비
		PayApproveRequestVO request = new PayApproveRequestVO();
		request.setPartnerOrderId("e31eab47-7327-4765-8113-6de445b663a8");
		request.setPartnerUserId("testuser1");
		request.setTid("T710f35c7f575eb0b335");
		request.setPgToken("539b780af43d29c1fba1");
		//처리
		PayApproveResponseVO response = 
				payService.approve(request);
		//출력
		log.info("response={}", response);
		
	}
}
