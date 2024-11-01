package com.kh.topgunFinal.vo.pay;

import lombok.Data;

@Data
public class PayReadyRequestVO {
	private int partnerOrderId;
	private String partnerUserId;
	private String itemName;
	private int totalAmount;
	private String approvalUrl;
	private String cancelUrl;
	private String failUrl;
	
 }
