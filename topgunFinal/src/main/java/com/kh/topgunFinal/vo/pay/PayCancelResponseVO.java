package com.kh.topgunFinal.vo.pay;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PayCancelResponseVO {
	private String aid;
	private String tid;
	private String cid;
	private String status;
	private String partnerOrderId;
	private String partnerUserId;
	private String paymentMethodType;
	private PayAmountVO amount;
	private PayAmountVO approvedCancelAmount;
	private PayAmountVO canceledAmount;
	private PayAmountVO cancelAvailableAmount;
	private String itemName;
	private String itemCode;
	private int quantity;
	private LocalDateTime createdAt;
	private LocalDateTime approvedAt;
	private LocalDateTime canceledAt;
	private String payload;
}
