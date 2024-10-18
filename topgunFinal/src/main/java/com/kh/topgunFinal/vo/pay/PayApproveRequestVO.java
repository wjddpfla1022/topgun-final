 package com.kh.topgunFinal.vo.pay;

import lombok.Data;

@Data
public class PayApproveRequestVO {
	private String partnerOrderId;
	private String partnerUserId;
	private String tid;
	private String pgToken;
}
