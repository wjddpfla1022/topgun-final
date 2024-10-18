package com.kh.topgunFinal.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kh.topgunFinal.configuration.PayProperties;
import com.kh.topgunFinal.vo.pay.PayApproveResponseVO;
import com.kh.topgunFinal.vo.pay.PayApproveRequestVO;
import com.kh.topgunFinal.vo.pay.PayReadyRequestVO;
import com.kh.topgunFinal.vo.pay.PayReadyResponseVO;

@Service
public class PayService {

	@Autowired
	private PayProperties payProperties;//cid, secret
	@Autowired
	private RestTemplate template;//PayConfiguration
	@Autowired
	private HttpHeaders headers;//PayConfiguration
	
	//결제 준비(ready)
		public PayReadyResponseVO ready(PayReadyRequestVO request) throws URISyntaxException {
			URI uri = new URI("https://open-api.kakaopay.com/online/v1/payment/ready");
			
			Map<String, String> body = new HashMap<>();	
			body.put("cid", payProperties.getCid());
			body.put("partner_order_id", request.getPartnerOrderId());
			body.put("partner_user_id", request.getPartnerUserId());
			body.put("item_name", request.getItemName());
			body.put("quantity", "1");
			body.put("total_amount", String.valueOf(request.getTotalAmount()));
			body.put("tax_free_amount", "0");
			body.put("approval_url", request.getApprovalUrl() + "/" + request.getPartnerOrderId());
			body.put("cancel_url", request.getCancelUrl());
			body.put("fail_url", request.getFailUrl());
			
			HttpEntity entity = new HttpEntity(body, headers);
			
			PayReadyResponseVO response = 
					template.postForObject(uri, entity, PayReadyResponseVO.class);
			
			return response;
		}
	
	//결제 승인(approve)
	public PayApproveResponseVO approve(PayApproveRequestVO request) throws URISyntaxException {
		URI uri = new URI("https://open-api.kakaopay.com/online/v1/payment/approve");
		
		Map<String, String> body = new HashMap<>();	
		body.put("cid", payProperties.getCid());
		body.put("partner_order_id", request.getPartnerOrderId());
		body.put("partner_user_id", request.getPartnerUserId());
		body.put("tid", request.getTid());
		body.put("pg_token", request.getPgToken());
		
		HttpEntity entity = new HttpEntity(body, headers);
		
		PayApproveResponseVO response= 
				template.postForObject(uri, entity, PayApproveResponseVO.class);
		
		return response;
	}	
}
