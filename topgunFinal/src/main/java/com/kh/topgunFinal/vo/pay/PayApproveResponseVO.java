package com.kh.topgunFinal.vo.pay;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kh.topgunFinal.vo.SeatsFlightInfoVO;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PayApproveResponseVO {
	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partnerOrderId;
	private String partnerUserId;
	private String paymentMethodType;
	private PayAmountVO amount;
	private PayCardInfoVO cardInfo;
	private String itemName;
	private String itemCode;
	private int quantity;
	private LocalDateTime createdAt;
	private LocalDateTime approvedAt;
	private String payload;
}
