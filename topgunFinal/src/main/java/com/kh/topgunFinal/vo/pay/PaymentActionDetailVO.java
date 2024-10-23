package com.kh.topgunFinal.vo.pay;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentActionDetailVO {
	private String aid;
	private LocalDateTime approvedAt;
	private int amount;
	private int pointAmount;
	private int discountAmount;
	private int greenDeposit;
	private String paymentActionType;
	private String payload;
}
